package com.mehdi.bankaccount.application.port.entrypoint.api;

import com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto.BankAccountDto;

public interface PostOperationEndpointPort {
    BankAccountDto revertOperation(Long operationId);
}
