package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.exception.ServiceException;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BuildingService {
    List<BuildingSearchResponse> findAll(BuildingSearchRequest params, Pageable pageable);

    BuildingDTO findBuildingById(Long id) throws ServiceException;

    BuildingEntity createOrUpdateBuilding(BuildingDTO buildingDTO) throws ServiceException;

    void deleteById(List<Long> ids);

    ResponseDTO findStaffByBuildingId(Long buildingId);

    int countBuilding (BuildingSearchRequest buildingSearchRequest);

    boolean isStaffOfBuilding (Long staffId, Long buildingId);

}
