package com.mehdi.bankaccount.application.service;

import com.mehdi.bankaccount.application.port.persistence.ReadBankAccountPort;
import com.mehdi.bankaccount.application.port.persistence.WriteBankAccountPort;
import com.mehdi.bankaccount.application.port.persistence.WriteOperationPort;
import com.mehdi.bankaccount.application.usecase.WithdrawFromBankAccountUseCase;
import com.mehdi.bankaccount.domain.AccountNumber;
import com.mehdi.bankaccount.domain.BankAccount;
import com.mehdi.bankaccount.domain.Operation;
import com.mehdi.bankaccount.domain.enums.AccountType;
import com.mehdi.bankaccount.domain.enums.OperationType;
import com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto.UpdateBankAccountDto;
import org.springframework.stereotype.Service;

import static java.time.LocalDateTime.now;

@Service
class WithdrawFromBankAccountService implements WithdrawFromBankAccountUseCase {

    private final ReadBankAccountPort readBankAccountPort;
    private final WriteBankAccountPort writeBankAccountPort;
    private final WriteOperationPort writeOperationPort;

    public WithdrawFromBankAccountService(ReadBankAccountPort readBankAccountPort, WriteBankAccountPort writeBankAccountPort, WriteOperationPort writeOperationPort) {
        this.readBankAccountPort = readBankAccountPort;
        this.writeBankAccountPort = writeBankAccountPort;
        this.writeOperationPort = writeOperationPort;
    }

    @Override
    public BankAccount withdraw(UpdateBankAccountDto updateBankAccountDto) {
        if (updateBankAccountDto.amount() <= 0)
            throw new RuntimeException("Withdraw amount must be greater than 0");
        BankAccount bankAccount = readBankAccountPort.loadAccount( new AccountNumber(updateBankAccountDto.accountNumber()));


        double newBalance = bankAccount.getBalance() - updateBankAccountDto.amount();

        // Business logic
        if ( newBalance < 0 && bankAccount.getAccountType().equals(AccountType.LIVRET.getType()))
            throw new RuntimeException("Insufficient funds, overdraft is disabled for livret accounts");

        if ( newBalance < bankAccount.getOverdraft()*(-1) )
            throw new RuntimeException("Insufficient funds, overdraft amount is exceeded");

        bankAccount.setBalance(newBalance);

        bankAccount = writeBankAccountPort.updateBankAccount(bankAccount);

        Operation operation = Operation.OperationBuilder.builder()
                .accountNumber(updateBankAccountDto.accountNumber())
                .operationType(OperationType.WITHDRAWAL.getType())
                .amount(updateBankAccountDto.amount())
                .operationDateTime(now())
                .build();
        writeOperationPort.createOperation(operation);


        return bankAccount;
    }
}
