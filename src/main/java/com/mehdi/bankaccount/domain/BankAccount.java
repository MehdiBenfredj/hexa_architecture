package com.mehdi.bankaccount.domain;

public class BankAccount {

    private AccountNumber accountNumber;
    private String accountType;
    private double balance;
    private double overdraft;

    public BankAccount() {
    }

    public BankAccount(AccountNumber accountNumber, String accountType, double balance, double overdraft) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.overdraft = overdraft;
    }

    public AccountNumber getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(AccountNumber accountNumber) {
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
        return "BankAccount{" +
                "accountNumber=" + accountNumber +
                ", accountType='" + accountType + '\'' +
                ", balance=" + balance +
                ", overdraft=" + overdraft +
                '}';
    }

    public static final class BankAccountBuilder {
        private String accountType;
        private AccountNumber accountNumber;
        private double balance;
        private double overdraft;

        public BankAccountBuilder() {
        }

        public static BankAccountBuilder BankAccount() {
            return new BankAccountBuilder();
        }

        public BankAccountBuilder accountType(String accountType) {
            this.accountType = accountType;
            return this;
        }

        public BankAccountBuilder accountNumber(AccountNumber accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public BankAccountBuilder balance(double balance) {
            this.balance = balance;
            return this;
        }

        public BankAccountBuilder overdraft(double overdraft) {
            this.overdraft = overdraft;
            return this;
        }

        public BankAccount build() {
            return new BankAccount(accountNumber, accountType, balance, overdraft);
        }
    }
}
