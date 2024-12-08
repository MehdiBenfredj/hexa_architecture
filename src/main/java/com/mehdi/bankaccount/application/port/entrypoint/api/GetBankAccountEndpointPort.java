package com.mehdi.bankaccount.application.port.entrypoint.api;

import com.mehdi.bankaccount.domain.AccountNumber;
import com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto.BankAccountDto;

import java.util.Collection;

public interface GetBankAccountEndpointPort {
    Collection<BankAccountDto> getAllAccounts();
    BankAccountDto getAccount(AccountNumber accountNumber);
}
