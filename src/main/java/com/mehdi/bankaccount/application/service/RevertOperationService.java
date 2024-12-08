package com.mehdi.bankaccount.application.service;

import com.mehdi.bankaccount.application.port.persistence.ReadOperationPort;
import com.mehdi.bankaccount.application.usecase.DepositToBankAccountUseCase;
import com.mehdi.bankaccount.application.usecase.RevertOperationUseCase;
import com.mehdi.bankaccount.application.usecase.WithdrawFromBankAccountUseCase;
import com.mehdi.bankaccount.domain.BankAccount;
import com.mehdi.bankaccount.domain.Operation;
import com.mehdi.bankaccount.domain.enums.OperationType;
import com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto.UpdateBankAccountDto;
import org.springframework.stereotype.Service;

@Service
class RevertOperationService implements RevertOperationUseCase {


    private final ReadOperationPort readOperationPort;
    private final DepositToBankAccountUseCase depositToBankAccountUseCase;
    private final WithdrawFromBankAccountUseCase withdrawFromBankAccountUseCase;

    public RevertOperationService(ReadOperationPort readOperationPort, DepositToBankAccountUseCase depositToBankAccountUseCase, WithdrawFromBankAccountUseCase withdrawFromBankAccountUseCase) {
        this.readOperationPort = readOperationPort;
        this.depositToBankAccountUseCase = depositToBankAccountUseCase;
        this.withdrawFromBankAccountUseCase = withdrawFromBankAccountUseCase;
    }

    @Override
    public BankAccount revertOperation(Long operationId) {
        Operation operation = readOperationPort.loadOperationById(operationId);
        String revertedOperationType = operation.getOperationType().equalsIgnoreCase("Deposit") ?
                OperationType.WITHDRAWAL.getType() : OperationType.DEPOSIT.getType();

        return revertedOperationType.equalsIgnoreCase("Deposit") ?
                depositToBankAccountUseCase.deposit( new UpdateBankAccountDto(operation.getAccountNumber(), operation.getAmount()) ) : withdrawFromBankAccountUseCase.withdraw( new UpdateBankAccountDto(operation.getAccountNumber(), operation.getAmount()));
    }
}
