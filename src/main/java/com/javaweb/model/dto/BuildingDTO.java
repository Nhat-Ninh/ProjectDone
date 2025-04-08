package com.javaweb.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class BuildingDTO extends AbstractDTO{
    private Long id;
    @NotBlank(message ="Name can not blank!")
    private String name;
    @NotBlank(message ="Street can not blank!")
    private String street;
    @NotBlank(message ="Ward can not blank!")
    private String ward;
    @NotBlank(message ="District can not blank!")
    private String district;
    private Long numberOfBasement;
    @NotNull(message = "Floor area can not null!")
    private Long floorArea;
    private String level;
    @Size(min = 1, message = "Building's type is required!")
    private List<String> typeCode;
    private String overtimeFee;
    private String electricityFee;
    private String deposit;
    private String payment;
    private String rentTime;
    private String decorationTime;
    @NotBlank(message = "Rent price description can not null!")
    private String rentPriceDescription;
    private String carFee;
    private String motoFee;
    private String structure;
    private String direction;
    private String note;
    @NotBlank(message = "Rent area can not null!")
    private String rentArea;
    @NotBlank(message = "Manager's name can not null!")
    private String managerName;
    @NotBlank(message = "Manager's phone number can not null!")
    private String managerPhone;
    private Long rentPrice;
    private String serviceFee;
    private double brokerageFee;
    private Long waterFee;
    private String image;
    private String imageBase64;
    private String imageName;

    private Map<String,String> buildingDTOs = new HashMap<>();

    public Map<String, String> getBuildingDTOs() {
        return buildingDTOs;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setBuildingDTOs(Map<String, String> buildingDTOs) {
        this.buildingDTOs = buildingDTOs;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getStructure() {
        return structure;
    }


    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageBase64() {
        if (imageBase64 != null) {
            return imageBase64.split(",")[1];
        }
        return null;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

}