package com.mehdi.bankaccount.application.usecase;

import com.mehdi.bankaccount.domain.BankAccount;
import com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto.CreateBankAccountDto;


public interface CreateNewBankAccountUseCase {
    BankAccount createNewAccount(CreateBankAccountDto createBankAccountDto);
}
