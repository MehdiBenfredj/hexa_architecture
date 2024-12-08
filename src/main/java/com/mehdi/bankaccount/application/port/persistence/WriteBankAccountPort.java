package com.mehdi.bankaccount.application.port.persistence;


import com.mehdi.bankaccount.domain.AccountNumber;
import com.mehdi.bankaccount.domain.BankAccount;

public interface WriteBankAccountPort {
    BankAccount createAccount(BankAccount bankAccount);
    BankAccount updateBankAccount(BankAccount bankAccount);
    boolean deleteBankAccount(AccountNumber accountNumber);
}
