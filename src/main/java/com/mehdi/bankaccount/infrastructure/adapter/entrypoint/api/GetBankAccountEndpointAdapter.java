package com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api;

import com.mehdi.bankaccount.application.port.entrypoint.api.GetBankAccountEndpointPort;
import com.mehdi.bankaccount.application.usecase.ReadAccountByIdUseCase;
import com.mehdi.bankaccount.application.usecase.ReadAllBankAccountsUseCase;
import com.mehdi.bankaccount.domain.AccountNumber;
import com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto.BankAccountDto;
import com.mehdi.bankaccount.infrastructure.adapter.persistence.mappers.BankAccountMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
class GetBankAccountEndpointAdapter implements GetBankAccountEndpointPort {

    private final ReadAllBankAccountsUseCase readAllBankAccountsUseCase;
    private final ReadAccountByIdUseCase readAccountByIdUseCase;
    private final BankAccountMapper bankAccountMapper;

    public GetBankAccountEndpointAdapter(ReadAllBankAccountsUseCase readAllBankAccountsUseCase, ReadAccountByIdUseCase readAccountByIdUseCase, BankAccountMapper bankAccountMapper) {
        this.readAllBankAccountsUseCase = readAllBankAccountsUseCase;
        this.readAccountByIdUseCase = readAccountByIdUseCase;
        this.bankAccountMapper = bankAccountMapper;
    }

    @Override
    public Collection<BankAccountDto> getAllAccounts() {
        return readAllBankAccountsUseCase.readAllBankAccounts().stream()
                .map((bankAccountMapper::toBankAccountDto))
                .collect(Collectors.toList());
    }

    @Override
    public BankAccountDto getAccount(AccountNumber accountNumber) {
        return bankAccountMapper.toBankAccountDto(readAccountByIdUseCase.readAccountById(accountNumber));
    }
}
