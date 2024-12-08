package com.mehdi.bankaccount.infrastructure.adapter.persistence.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "bank_account")
public class BankAccountEntity {

    @Id
    @UuidGenerator
    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "balance")
    private double balance;

    @Column(name = "overdraft")
    private double overdraft;

    public BankAccountEntity() {
    }

    public BankAccountEntity(String accountNumber, String accountType, double balance, double overdraft) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.overdraft = overdraft;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
    }

    @Override
    public String toString() {
        return "BankAccountEntity{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountType=" + accountType +
                ", balance=" + balance +
                ", overdraft=" + overdraft +
                '}';
    }
}
