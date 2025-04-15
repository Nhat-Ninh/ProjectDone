package com.javaweb.service.impl;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.exception.ServiceException;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.BuildingService;
import com.javaweb.utils.DataUtil;
import com.javaweb.utils.UploadFileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Autowired
    private BuildingConverter buildingConverter;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;
    @Autowired
    private UploadFileUtils uploadFileUtils;

    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest params, Pageable pageable) {
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(params,pageable);

        List<BuildingSearchResponse> results = new ArrayList<BuildingSearchResponse>();
        for (BuildingEntity it : buildingEntities) {
            BuildingSearchResponse buildingResponse = buildingConverter.toBuildingResponseDTO(it);
            results.add(buildingResponse);
        }
        return results;
    }
    @Override
    public int countBuilding(BuildingSearchRequest buildingSearchRequest) {
        return buildingRepository.countBuilding(buildingSearchRequest);
    }

    @Override
    public boolean isStaffOfBuilding(Long staffId, Long buildingId) {
        BuildingEntity buildingEntity = buildingRepository.findBuildingEntityById(buildingId);
        UserEntity userEntity = userRepository.findById(staffId).get();
        if(buildingEntity.getStaffs().contains(userEntity)) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public BuildingDTO findBuildingById(Long id) throws ServiceException{
        BuildingEntity buildingEntitySearch = buildingRepository.findBuildingEntityById(id);
        if(buildingEntitySearch == null){
            throw new ServiceException("Id building invalid");
        }
        BuildingDTO buildingDTO = modelMapper.map(buildingRepository.findBuildingEntityById(id), BuildingDTO.class);

        List<RentAreaEntity> rentAreas = rentAreaRepository.findByBuildingEntity_Id(id);
        DecimalFormat formatter = new DecimalFormat("#,###");  // Định dạng có dấu,
        List<String> formattedRentAreas = rentAreas.stream()
                .map(rentArea -> formatter.format(rentArea.getValue())).collect(Collectors.toList());
        buildingDTO.setRentArea(String.join(", ", formattedRentAreas)); //Loại bỏ []

        BuildingEntity buildingEntity = buildingRepository.findBuildingEntityById(id);
        String typeCode = buildingEntity.getType();
        buildingDTO.setTypeCode(Arrays.asList(typeCode.split(",")));


        return buildingDTO;
    }

    @Override
    public BuildingEntity createOrUpdateBuilding(BuildingDTO buildingDTO) throws ServiceException {
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        //luu anh
        if(buildingDTO.getId()!= null){
            BuildingEntity buildingEx = buildingRepository.findBuildingEntityById(buildingDTO.getId());
            buildingEntity.setImage(buildingEx.getImage());
        }
        saveThumbnail(buildingDTO, buildingEntity);
        if(buildingDTO.getId() != null){
            BuildingEntity buildingEntity1 = buildingRepository.findBuildingEntityById(buildingDTO.getId());
            buildingEntity.setStaffs(buildingEntity1.getStaffs());
        }
        //save TH ngoai le
        if(buildingDTO.getTypeCode() != null && buildingDTO.getTypeCode().size()!=0){
            List<String> typeCode = buildingDTO.getTypeCode();
            buildingEntity.setType(typeCode.stream().collect(Collectors.joining(",")));
        }
        buildingRepository.save(buildingEntity);

        List<RentAreaEntity> rentAreaEntity = new ArrayList<>();
        if(DataUtil.checkData(buildingDTO.getRentArea())){
            String[] rentAreaValues = buildingDTO.getRentArea().split(",");
            for(String value : rentAreaValues){
               try{
                   Long rentAreaValue = Long.parseLong(value.trim());
                   RentAreaEntity rentAreaEntityEntity = new RentAreaEntity();
                   rentAreaEntityEntity.setValue(rentAreaValue);
                   rentAreaEntityEntity.setBuildingEntity(buildingEntity);
                   rentAreaEntity.add(rentAreaEntityEntity);
                   rentAreaRepository.save(rentAreaEntityEntity);
               }
               catch(ServiceException ex){
                   throw new ServiceException("Rent Area DATA: " +ex.getMessage());
               }
            }
        }
        return null;
    }

    @Override
    public void deleteById(List<Long> ids){
     buildingRepository.deleteByIdIn(ids);
    }

    @Override
    public ResponseDTO findStaffByBuildingId(Long buildingId){
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1,"STAFF");
        BuildingEntity buildingEntity = buildingRepository.findBuildingEntityById(buildingId);
        List<UserEntity> staffss = buildingEntity.getStaffs();
        List<StaffResponseDTO> staffResponseDTOs = new ArrayList<>(); // danh sach nhan vien tra ra
        for(UserEntity staff : staffs){
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setStaffId(staff.getId());
            staffResponseDTO.setFullName(staff.getFullName());
            if(staffss.contains(staff)){
                staffResponseDTO.setChecked("checked");
            }
            else{
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOs.add(staffResponseDTO);
        }
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(staffResponseDTOs);
        responseDTO.setMessage("success");
        return responseDTO;
    }

    private void saveThumbnail(@NotNull BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        String path = "/building/" + buildingDTO.getImageName();
        if (null != buildingDTO.getImageBase64()) {
            if (null != buildingEntity.getImage()) {
                if (!path.equals(buildingEntity.getImage())) {
                    File file = new File("C://home/office" + buildingEntity.getImage());
                    file.delete();
                }
            }
            byte[] bytes = Base64.decodeBase64(buildingDTO.getImageBase64().getBytes());
            uploadFileUtils.writeOrUpdate(path, bytes);
            buildingEntity.setImage(path);
        }
    }


}
