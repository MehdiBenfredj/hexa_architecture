package com.mehdi.bankaccount.infrastructure.adapter.persistence.adapters;

import com.mehdi.bankaccount.application.port.persistence.ReadBankAccountPort;
import com.mehdi.bankaccount.domain.AccountNumber;
import com.mehdi.bankaccount.domain.BankAccount;
import com.mehdi.bankaccount.infrastructure.adapter.persistence.mappers.BankAccountEntityMapper;
import com.mehdi.bankaccount.infrastructure.adapter.persistence.model.BankAccountEntity;
import com.mehdi.bankaccount.infrastructure.adapter.persistence.repository.BankAccountRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
class ReadBankAccountAdapter implements ReadBankAccountPort {

    private final BankAccountRepository bankAccountRepository;
    private final BankAccountEntityMapper bankAccountEntityMapper;

    public ReadBankAccountAdapter(BankAccountRepository bankAccountRepository, BankAccountEntityMapper bankAccountEntityMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.bankAccountEntityMapper = bankAccountEntityMapper;
    }

    @Override
    public BankAccount loadAccount(AccountNumber accountNumber) {
        BankAccountEntity bankAccountEntity = bankAccountRepository.findByAccountNumber(accountNumber.accountNumber())
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return bankAccountEntityMapper.toBankAccount(bankAccountEntity);
    }

    @Override
    public Collection<BankAccount> loadAllAccounts() {
        return bankAccountRepository.findAll().stream()
                .map(bankAccountEntityMapper::toBankAccount)
                .collect(Collectors.toList());
    }
}
