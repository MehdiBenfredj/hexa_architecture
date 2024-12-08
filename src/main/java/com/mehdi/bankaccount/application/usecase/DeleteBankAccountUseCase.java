package com.mehdi.bankaccount.application.usecase;

import com.mehdi.bankaccount.domain.AccountNumber;

public interface DeleteBankAccountUseCase {
    void deleteBankAccountById(AccountNumber accountNumber);
}
