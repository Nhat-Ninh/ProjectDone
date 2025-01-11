package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.enums.District;
import com.javaweb.model.response.BuildingSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {
    @Autowired
    private ModelMapper modelMapper;
    public BuildingSearchResponse toBuildingResponseDTO(BuildingEntity it) {
        BuildingSearchResponse buildingSearchResponse = modelMapper.map(it, BuildingSearchResponse.class);
        District district = Enum.valueOf(District.class, it.getDistrict());
        buildingSearchResponse.setAddress(it.getStreet() + ", " + it.getWard() + ", " + district.getDistrictName());

        List<RentAreaEntity> rentAreaEntities = it.getRentareas();
        String rentArea = rentAreaEntities.stream().map(i -> i.getValue().toString()).collect(Collectors.joining(","));
        buildingSearchResponse.setRentArea(rentArea);

        return buildingSearchResponse;
    }

}
