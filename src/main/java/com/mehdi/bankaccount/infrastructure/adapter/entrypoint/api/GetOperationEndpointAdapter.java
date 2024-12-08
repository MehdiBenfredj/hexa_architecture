package com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api;

import com.mehdi.bankaccount.application.port.entrypoint.api.GetOperationEndpointPort;
import com.mehdi.bankaccount.application.usecase.GetAccountStatementUseCase;
import com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto.AccountStatementDto;
import com.mehdi.bankaccount.infrastructure.adapter.persistence.mappers.AccountStatementMapper;
import org.springframework.stereotype.Component;

@Component
class GetOperationEndpointAdapter implements GetOperationEndpointPort {
    private final GetAccountStatementUseCase getAccountStatementUseCase;
    private final AccountStatementMapper accountStatementMapper;

    public GetOperationEndpointAdapter(GetAccountStatementUseCase getAccountStatementUseCase, AccountStatementMapper accountStatementMapper) {
        this.getAccountStatementUseCase = getAccountStatementUseCase;
        this.accountStatementMapper = accountStatementMapper;
    }

    @Override
    public AccountStatementDto getAccountStatement(String accountNumber) {
        return accountStatementMapper.toAccountStatementDto( getAccountStatementUseCase.getAccountStatement(accountNumber) );
    }

}
