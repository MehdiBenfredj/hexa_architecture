package com.mehdi.bankaccount.application.usecase;

import com.mehdi.bankaccount.domain.AccountStatement;

public interface GetAccountStatementUseCase {
    AccountStatement getAccountStatement(String accountNumber);
}
