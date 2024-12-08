package com.mehdi.bankaccount.application.service;

import com.mehdi.bankaccount.application.port.persistence.ReadBankAccountPort;
import com.mehdi.bankaccount.application.port.persistence.WriteBankAccountPort;
import com.mehdi.bankaccount.application.port.persistence.WriteOperationPort;
import com.mehdi.bankaccount.application.usecase.DepositToBankAccountUseCase;
import com.mehdi.bankaccount.domain.AccountNumber;
import com.mehdi.bankaccount.domain.BankAccount;
import com.mehdi.bankaccount.domain.Operation;
import com.mehdi.bankaccount.domain.enums.OperationType;
import com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto.UpdateBankAccountDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.time.LocalDateTime.now;

@Service
class DepositToBankAccountService implements DepositToBankAccountUseCase {

    private final WriteBankAccountPort writeBankAccountPort;
    private final ReadBankAccountPort readBankAccountPort;
    private final WriteOperationPort writeOperationPort;

    public DepositToBankAccountService(WriteBankAccountPort writeBankAccountPort, ReadBankAccountPort readBankAccountPort, WriteOperationPort writeOperationPort) {
        this.writeBankAccountPort = writeBankAccountPort;
        this.readBankAccountPort = readBankAccountPort;
        this.writeOperationPort = writeOperationPort;
    }

    @Override
    @Transactional
    public BankAccount deposit(UpdateBankAccountDto updateBankAccountDto) {
        if (updateBankAccountDto.amount() <= 0)
            throw new RuntimeException("Deposit amount must be greater than 0");

        BankAccount bankAccount = readBankAccountPort.loadAccount( new AccountNumber(updateBankAccountDto.accountNumber()));

        double newBalance = bankAccount.getBalance() + updateBankAccountDto.amount();
        bankAccount.setBalance(newBalance);

        bankAccount = writeBankAccountPort.updateBankAccount(bankAccount);

        Operation operation = Operation.OperationBuilder.builder()
                .accountNumber(updateBankAccountDto.accountNumber())
                .operationType(OperationType.DEPOSIT.getType())
                .amount(updateBankAccountDto.amount())
                .operationDateTime(now())
                .build();

        writeOperationPort.createOperation(operation);

        return bankAccount;
    }
}
