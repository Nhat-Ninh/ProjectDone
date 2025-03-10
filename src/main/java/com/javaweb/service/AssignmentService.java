package com.javaweb.service;

import com.javaweb.exception.ServiceException;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.AssignmentCustomerDTO;

public interface AssignmentService {
    void updateAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) throws ServiceException;
    void updateAssignmentCustomer(AssignmentCustomerDTO assignmentCustomerDTO) throws ServiceException;
}
