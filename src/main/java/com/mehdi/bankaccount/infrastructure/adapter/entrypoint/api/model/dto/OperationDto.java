package com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto;

import java.time.LocalDateTime;

public record OperationDto(Long operationId, String operationType, double amount, LocalDateTime operationDateTime) {

}
