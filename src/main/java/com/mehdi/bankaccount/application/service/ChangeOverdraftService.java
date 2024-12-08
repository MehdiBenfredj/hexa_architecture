package com.mehdi.bankaccount.application.service;

import com.mehdi.bankaccount.application.port.persistence.ReadBankAccountPort;
import com.mehdi.bankaccount.application.port.persistence.WriteBankAccountPort;
import com.mehdi.bankaccount.application.usecase.ChangeOverdraftUseCase;
import com.mehdi.bankaccount.domain.AccountNumber;
import com.mehdi.bankaccount.domain.BankAccount;
import com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto.UpdateBankAccountDto;
import org.springframework.stereotype.Service;

@Service
class ChangeOverdraftService implements ChangeOverdraftUseCase {

    private final WriteBankAccountPort writeBankAccountPort;
    private final ReadBankAccountPort readBankAccountPort;

    public ChangeOverdraftService(WriteBankAccountPort writeBankAccountPort, ReadBankAccountPort readBankAccountPort) {
        this.writeBankAccountPort = writeBankAccountPort;
        this.readBankAccountPort = readBankAccountPort;
    }

    @Override
    public BankAccount changeOverdraft(UpdateBankAccountDto updateBankAccountDto) {
        if (updateBankAccountDto.amount() <= 0)
            throw new RuntimeException("Overdraft amount must be greater than 0");

        BankAccount bankAccount = readBankAccountPort.loadAccount(new AccountNumber(updateBankAccountDto.accountNumber()));

        if ( bankAccount.getAccountType().equalsIgnoreCase("livret") )
            throw new RuntimeException("Cannot have overdraft for this account type");

        bankAccount.setOverdraft(updateBankAccountDto.amount());
        writeBankAccountPort.updateBankAccount(bankAccount);
        return bankAccount;
    }
}
