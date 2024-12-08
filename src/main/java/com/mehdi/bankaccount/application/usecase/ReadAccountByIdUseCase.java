package com.mehdi.bankaccount.application.usecase;

import com.mehdi.bankaccount.domain.AccountNumber;
import com.mehdi.bankaccount.domain.BankAccount;

public interface ReadAccountByIdUseCase {
    BankAccount readAccountById(AccountNumber accountNumber);
}
