package com.mehdi.bankaccount.application.usecase;

import com.mehdi.bankaccount.domain.BankAccount;
import com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto.UpdateBankAccountDto;

public interface DepositToBankAccountUseCase {
    BankAccount deposit(UpdateBankAccountDto updateBankAccountDto);
}
