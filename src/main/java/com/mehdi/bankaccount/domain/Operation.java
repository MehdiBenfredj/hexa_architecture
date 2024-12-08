package com.mehdi.bankaccount.domain;

import java.time.LocalDateTime;

public class Operation {

    private final Long operationId;
    private final String accountNumber;
    private String operationType;
    private double amount;
    private LocalDateTime operationDateTime;

    public Operation(Long operationId, String accountNumber, String operationType, double amount, LocalDateTime operationDateTime) {
        this.operationId = operationId;
        this.accountNumber = accountNumber;
        this.operationType = operationType;
        this.amount = amount;
        this.operationDateTime = operationDateTime;
    }

    public Long getOperationId() {
        return operationId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getOperationDateTime() {
        return operationDateTime;
    }

    public void setOperationDateTime(LocalDateTime operationDateTime) {
        this.operationDateTime = operationDateTime;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "operationId=" + operationId +
                ", accountNumber='" + accountNumber + '\'' +
                ", operationType='" + operationType + '\'' +
                ", amount=" + amount +
                ", operationDateTime=" + operationDateTime +
                '}';
    }

    public static final class OperationBuilder {
        private Long operationId;
        private String accountNumber;
        private String operationType;
        private double amount;
        private LocalDateTime operationDateTime;



        public static OperationBuilder builder() {
            return new OperationBuilder();
        }

        public OperationBuilder operationId(Long operationId) {
            this.operationId = operationId;
            return this;
        }

        public OperationBuilder accountNumber(String accountNumber){
            this.accountNumber = accountNumber;
            return this;
        }

        public OperationBuilder operationType(String operationType) {
            this.operationType = operationType;
            return this;
        }

        public OperationBuilder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public OperationBuilder operationDateTime(LocalDateTime operationDateTime) {
            this.operationDateTime = operationDateTime;
            return this;
        }

        public Operation build() { return new Operation(operationId, accountNumber, operationType, amount, operationDateTime); }
    }
}
