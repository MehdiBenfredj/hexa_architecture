package com.mehdi.bankaccount.application.service;

import com.mehdi.bankaccount.application.port.persistence.WriteBankAccountPort;
import com.mehdi.bankaccount.application.usecase.CreateNewBankAccountUseCase;
import com.mehdi.bankaccount.domain.BankAccount;
import com.mehdi.bankaccount.domain.enums.AccountType;
import com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto.CreateBankAccountDto;
import org.springframework.stereotype.Service;

@Service
class CreateNewBankAccountService implements CreateNewBankAccountUseCase {

    private final WriteBankAccountPort writeBankAccountPort;

    public CreateNewBankAccountService(WriteBankAccountPort writeBankAccountPort) {
        this.writeBankAccountPort = writeBankAccountPort;
    }


    @Override
    public BankAccount createNewAccount(CreateBankAccountDto createBankAccountDto) {
        if (createBankAccountDto.initialBalance() < 0 || createBankAccountDto.initialOverdraft() < 0)
            throw new RuntimeException("Initial balance and overdraft must be positive");


        String accountType = switch (createBankAccountDto.accountType().toLowerCase()) {
            case "depot" -> AccountType.DEPOT.getType();
            case "livret" -> AccountType.LIVRET.getType();
            default -> throw new RuntimeException("Account type not supported");
        };

        if ( accountType.equalsIgnoreCase("livret") &&
                createBankAccountDto.initialOverdraft() != 0 )
                throw new RuntimeException("Livret type account cannot have overdraft");

        double initialOverdraft = createBankAccountDto.initialOverdraft();

        double initialBalance = createBankAccountDto.initialBalance();

        BankAccount bankAccount = new BankAccount.BankAccountBuilder()
                .accountType(accountType)
                .balance(initialBalance)
                .overdraft(initialOverdraft)
                .build();

        return writeBankAccountPort.createAccount(bankAccount);
    }

}
