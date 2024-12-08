package com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto;

import java.util.List;

public record AccountStatementDto(String accountNumber, double balance, List<OperationDto> operationList) {
}
