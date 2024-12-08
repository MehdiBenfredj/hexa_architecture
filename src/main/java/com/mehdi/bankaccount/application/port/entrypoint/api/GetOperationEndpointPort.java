package com.mehdi.bankaccount.application.port.entrypoint.api;

import com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto.AccountStatementDto;

public interface GetOperationEndpointPort {
    AccountStatementDto getAccountStatement(String accountNumber);

}
