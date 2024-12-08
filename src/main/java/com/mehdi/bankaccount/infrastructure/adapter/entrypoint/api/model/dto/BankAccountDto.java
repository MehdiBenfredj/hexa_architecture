package com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto;

public record BankAccountDto(String accountNumber, String accountType, double balance, double overdraft) {
}
