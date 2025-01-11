package com.javaweb.service;

import com.javaweb.exception.ServiceException;
import com.javaweb.model.dto.AssignmentBuildingDTO;

public interface AssignmentService {
    void updateAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) throws ServiceException;
}
