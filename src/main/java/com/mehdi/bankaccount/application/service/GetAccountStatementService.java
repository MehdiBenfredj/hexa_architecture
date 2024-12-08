package com.mehdi.bankaccount.application.service;

import com.mehdi.bankaccount.application.port.persistence.ReadBankAccountPort;
import com.mehdi.bankaccount.application.port.persistence.ReadOperationPort;
import com.mehdi.bankaccount.application.usecase.GetAccountStatementUseCase;
import com.mehdi.bankaccount.domain.AccountNumber;
import com.mehdi.bankaccount.domain.AccountStatement;
import com.mehdi.bankaccount.domain.Operation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class GetAccountStatementService implements GetAccountStatementUseCase {

    private final ReadOperationPort readOperationPort;
    private final ReadBankAccountPort readBankAccountPort;

    public GetAccountStatementService(ReadOperationPort readOperationPort, ReadBankAccountPort readBankAccountPort) {
        this.readOperationPort = readOperationPort;
        this.readBankAccountPort = readBankAccountPort;
    }

    @Override
    public AccountStatement getAccountStatement(String accountNumber) {
        double balance = readBankAccountPort.loadAccount(new AccountNumber(accountNumber)).getBalance();
         List<Operation> operationList = readOperationPort.loadOperationsByBankAccount(accountNumber).stream()
                 .sorted((o1, o2) -> o2.getOperationDateTime().compareTo(o1.getOperationDateTime())).toList();
         return new AccountStatement(accountNumber, balance, operationList);
    }
}
