package com.mehdi.bankaccount.application.port.entrypoint.api;

import com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto.BankAccountDto;
import com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto.CreateBankAccountDto;
import com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto.UpdateBankAccountDto;

public interface PostBankAccountEndpointPort {
    BankAccountDto createAccount(CreateBankAccountDto createBankAccountDto);
    BankAccountDto deposit(UpdateBankAccountDto updateBankAccountDto);
    BankAccountDto withdraw(UpdateBankAccountDto updateBankAccountDto);
    void deleteAccount(String accountNumber);
}
