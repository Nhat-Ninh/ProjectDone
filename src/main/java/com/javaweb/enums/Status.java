package com.javaweb.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public enum Status {

    CHUA_XU_LY("Chưa xử lý"),
    DANG_XU_LY("Đang xử lý"),
    DA_XU_LY("Đã xử lý");

    private final String statusName;
    Status(String statusName) {
        this.statusName = statusName;
    }

    @JsonValue
    public String getStatusName() {
        return statusName;
    }

    public static Map<String, String> getStatus() {
        Map<String, String> status = new LinkedHashMap<String,String>();
        for (Status item : Status.values()) {
            status.put(item.toString(), item.getStatusName());
        }
        return status;
    }


}
