package com.mehdi.bankaccount.application.usecase;

import com.mehdi.bankaccount.domain.BankAccount;

import java.util.Collection;

public interface ReadAllBankAccountsUseCase {
    Collection<BankAccount> readAllBankAccounts();
}
