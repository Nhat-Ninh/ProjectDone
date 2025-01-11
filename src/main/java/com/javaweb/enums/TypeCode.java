package com.javaweb.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum TypeCode {
    TANG_TRET("Tầng trệt "),
    NGUYEN_CAN("Nguyên căn "),
    NOI_THAT("Nội thất ");


    private String typeName;
    TypeCode(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public static Map<String, String> getType() {
        Map<String, String> types = new LinkedHashMap<String,String>();
        for (TypeCode item : TypeCode.values()) {
            types.put(item.toString(), item.getTypeName());
        }
        return types;
    }
}
