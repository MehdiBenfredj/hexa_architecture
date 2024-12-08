package com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto;

public record CreateBankAccountDto(String accountType, double initialBalance, double initialOverdraft) {
}
