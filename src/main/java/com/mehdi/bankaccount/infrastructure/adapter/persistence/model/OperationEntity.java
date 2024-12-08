package com.mehdi.bankaccount.infrastructure.adapter.persistence.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "operation")
public class OperationEntity {

    @Id
    @Column(name = "operation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long operationId;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "operation_type")
    private String operationType;

    @Column(name = "amount")
    private double amount;

    @Column(name = "operation_date_time")
    private LocalDateTime operationDateTime;

    public OperationEntity() {}

    public OperationEntity(Long operationId, String accountNumber, double amount, LocalDateTime operationDateTime) {
        this.operationId = operationId;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.operationDateTime = operationDateTime;
    }

    public Long getOperationId() {
        return operationId;
    }

    public void setOperationId(Long operationId) {
        this.operationId = operationId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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

    public void setAmount(double amount) {
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
        return "OperationEntity{" +
                "operationId=" + operationId +
                ", accountNumber='" + accountNumber + '\'' +
                ", amount=" + amount +
                ", operationDateTime=" + operationDateTime +
                '}';
    }
}
