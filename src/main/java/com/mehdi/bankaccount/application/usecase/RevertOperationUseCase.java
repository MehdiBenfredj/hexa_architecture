package com.mehdi.bankaccount.application.usecase;

import com.mehdi.bankaccount.domain.BankAccount;

public interface RevertOperationUseCase {
    BankAccount revertOperation(Long operationId);
}
