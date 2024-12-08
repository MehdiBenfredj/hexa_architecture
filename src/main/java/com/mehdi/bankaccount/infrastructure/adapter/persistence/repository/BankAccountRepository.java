package com.mehdi.bankaccount.infrastructure.adapter.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mehdi.bankaccount.domain.AccountNumber;
import com.mehdi.bankaccount.infrastructure.adapter.persistence.model.BankAccountEntity;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountEntity, AccountNumber> {
    Optional<BankAccountEntity> findByAccountNumber(String accountNumber);
    void deleteByAccountNumber(String accountNumber);

}