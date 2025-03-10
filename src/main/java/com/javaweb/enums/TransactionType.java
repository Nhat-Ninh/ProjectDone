package com.javaweb.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public enum TransactionType {
    CSKH("Chăm sóc khách hàng"),
    DDX("Dẫn đi xem");

    private final String name;
    TransactionType(String name) {
        this.name = name;
    }
    @JsonValue
    public String getName() {
        return name;
    }
    public static Map<String, String> getTransactionType() {
        Map<String, String> transactionList = new LinkedHashMap<>();
        for (TransactionType transactionType : TransactionType.values()) {
            transactionList.put(transactionType.toString(), transactionType.getName());
        }
        return transactionList;
    }
}
