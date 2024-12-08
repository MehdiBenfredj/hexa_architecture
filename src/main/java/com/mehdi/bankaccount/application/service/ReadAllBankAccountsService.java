package com.mehdi.bankaccount.application.service;

import com.mehdi.bankaccount.application.port.persistence.ReadBankAccountPort;
import com.mehdi.bankaccount.application.usecase.ReadAllBankAccountsUseCase;
import com.mehdi.bankaccount.domain.BankAccount;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
class ReadAllBankAccountsService implements ReadAllBankAccountsUseCase {
    private final ReadBankAccountPort readBankAccountPort;

    public ReadAllBankAccountsService(ReadBankAccountPort readBankAccountPort) {
        this.readBankAccountPort = readBankAccountPort;
    }


    @Override
    public Collection<BankAccount> readAllBankAccounts() {
        return readBankAccountPort.loadAllAccounts();
    }
}
