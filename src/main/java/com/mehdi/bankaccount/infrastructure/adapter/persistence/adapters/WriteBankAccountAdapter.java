package com.mehdi.bankaccount.infrastructure.adapter.persistence.adapters;

import com.mehdi.bankaccount.application.port.persistence.WriteBankAccountPort;
import com.mehdi.bankaccount.domain.AccountNumber;
import com.mehdi.bankaccount.domain.BankAccount;
import com.mehdi.bankaccount.infrastructure.adapter.persistence.mappers.BankAccountEntityMapper;
import com.mehdi.bankaccount.infrastructure.adapter.persistence.model.BankAccountEntity;
import com.mehdi.bankaccount.infrastructure.adapter.persistence.repository.BankAccountRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
class WriteBankAccountAdapter implements WriteBankAccountPort {

    private final BankAccountRepository bankAccountRepository;
    private final BankAccountEntityMapper bankAccountEntityMapper;

    public WriteBankAccountAdapter(BankAccountRepository bankAccountRepository, BankAccountEntityMapper bankAccountEntityMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.bankAccountEntityMapper = bankAccountEntityMapper;
    }

    @Override
    @Transactional
    public BankAccount createAccount(BankAccount bankAccount) {
        return bankAccountEntityMapper.toBankAccount( bankAccountRepository.save(bankAccountEntityMapper.toBankAccountEntity(bankAccount)) );
    }

    @Override
    @Transactional
    public BankAccount updateBankAccount(BankAccount bankAccount) {
        BankAccountEntity bankAccountEntity = bankAccountEntityMapper.toBankAccountEntity(bankAccount);
        bankAccountRepository.save(bankAccountEntity);
        return bankAccountEntityMapper.toBankAccount( bankAccountEntity );
    }

    @Override
    @Transactional
    public boolean deleteBankAccount(AccountNumber accountNumber) {
        bankAccountRepository.deleteByAccountNumber(accountNumber.accountNumber());
        return true;
    }
}
