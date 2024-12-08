package com.mehdi.bankaccount.application.port.persistence;

import com.mehdi.bankaccount.domain.AccountNumber;
import com.mehdi.bankaccount.domain.BankAccount;

import java.util.Collection;

public interface ReadBankAccountPort {
    BankAccount loadAccount(AccountNumber accountNumber);
    Collection<BankAccount> loadAllAccounts();
}
