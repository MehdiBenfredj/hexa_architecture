package com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api;

import com.mehdi.bankaccount.application.port.entrypoint.api.PostOperationEndpointPort;
import com.mehdi.bankaccount.application.usecase.RevertOperationUseCase;
import com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto.BankAccountDto;
import com.mehdi.bankaccount.infrastructure.adapter.persistence.mappers.BankAccountMapper;
import org.springframework.stereotype.Component;

@Component
class PostOperationEndpointAdapter implements PostOperationEndpointPort {

    private final RevertOperationUseCase revertOperationUseCase;
    private final BankAccountMapper bankAccountMapper;

    public PostOperationEndpointAdapter(RevertOperationUseCase revertOperationUseCase, BankAccountMapper bankAccountMapper) {
        this.revertOperationUseCase = revertOperationUseCase;
        this.bankAccountMapper = bankAccountMapper;
    }

    @Override
    public BankAccountDto revertOperation(Long operationId) {
        return bankAccountMapper.toBankAccountDto(revertOperationUseCase.revertOperation(operationId));
    }
}
