package com.mehdi.bankaccount.application.service;

import com.mehdi.bankaccount.application.port.persistence.ReadBankAccountPort;
import com.mehdi.bankaccount.application.port.persistence.WriteBankAccountPort;
import com.mehdi.bankaccount.application.usecase.DeleteBankAccountUseCase;
import com.mehdi.bankaccount.domain.AccountNumber;
import org.springframework.stereotype.Service;

@Service
class DeleteBankAccountService implements DeleteBankAccountUseCase {
    WriteBankAccountPort writeBankAccountPort;
    ReadBankAccountPort readBankAccountPort;

    public DeleteBankAccountService(WriteBankAccountPort writeBankAccountPort, ReadBankAccountPort readBankAccountPort) {
        this.writeBankAccountPort = writeBankAccountPort;
        this.readBankAccountPort = readBankAccountPort;
    }

    @Override
    public void deleteBankAccountById(AccountNumber accountNumber) {
        readBankAccountPort.loadAccount(accountNumber);
        writeBankAccountPort.deleteBankAccount(accountNumber);
    }
}
