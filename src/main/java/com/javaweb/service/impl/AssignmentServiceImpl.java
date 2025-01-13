package com.javaweb.service.impl;

import com.javaweb.constant.SystemConstant;
import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.exception.ServiceException;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AssignmentServiceImpl implements AssignmentService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public void updateAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) throws ServiceException {
        BuildingEntity buildingEntity = buildingRepository.findBuildingEntityById(assignmentBuildingDTO.getBuildingId());
        if(buildingEntity == null){
            throw new ServiceException(SystemConstant.BUILDING_NOT_FOUND);
        }

        List<UserEntity> userEntities = userRepository.findByIdIn(assignmentBuildingDTO.getStaffIds());
        buildingEntity.getStaffs().clear();
        buildingEntity.setStaffs(userEntities);

        buildingRepository.save(buildingEntity);
    }
}
