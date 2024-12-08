package com.mehdi.bankaccount.domain.enums;

public enum OperationType {
    DEPOSIT("Deposit"),
    WITHDRAWAL("Withdrawal");

    private final String operationType;

    OperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getType() {
        return operationType;
    }
}
