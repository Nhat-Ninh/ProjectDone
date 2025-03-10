package com.javaweb.service.impl;

import com.javaweb.constant.SystemConstant;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.exception.ServiceException;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public void updateAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) throws ServiceException {
        BuildingEntity buildingEntity = buildingRepository.findBuildingEntityById(assignmentBuildingDTO.getBuildingId());
        if(buildingEntity == null){
            throw new ServiceException(SystemConstant.BUILDING_NOT_FOUND);
        }
        List<UserEntity> userEntities = userRepository.findByIdIn(assignmentBuildingDTO.getStaffIds());
//        buildingEntity.getStaffs().clear(); ko can phai xoa vi manytomany giup delete r   
        buildingEntity.setStaffs(userEntities);
        buildingRepository.save(buildingEntity);
    }

    @Override
    public void updateAssignmentCustomer(AssignmentCustomerDTO assignmentCustomerDTO) throws ServiceException {
        CustomerEntity customerEntity = customerRepository.findCustomerEntityById(assignmentCustomerDTO.getCustomerId());
        if(customerEntity == null){
            throw new ServiceException(SystemConstant.ERROR_SYSTEM);
        }
        List<UserEntity> userEntities = userRepository.findByIdIn(assignmentCustomerDTO.getStaffIds());
        customerEntity.setStaffs(userEntities);
        customerRepository.save(customerEntity);
    }
}
