package com.mehdi.bankaccount.application.service;

import com.mehdi.bankaccount.application.port.persistence.ReadBankAccountPort;
import com.mehdi.bankaccount.application.usecase.ReadAccountByIdUseCase;
import com.mehdi.bankaccount.domain.AccountNumber;
import com.mehdi.bankaccount.domain.BankAccount;
import org.springframework.stereotype.Service;

@Service
class ReadBankAccountService implements ReadAccountByIdUseCase {

    private final ReadBankAccountPort readBankAccountPort;

    public ReadBankAccountService(ReadBankAccountPort readBankAccountPort) {
        this.readBankAccountPort = readBankAccountPort;
    }

    @Override
    public BankAccount readAccountById(AccountNumber accountNumber) {
        return readBankAccountPort.loadAccount(accountNumber);
    }
}
