package com.mehdi.bankaccount.domain.enums;

public enum AccountType {
    DEPOT("Depot"),
    LIVRET("Livret");

    private final String type;

    AccountType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}


