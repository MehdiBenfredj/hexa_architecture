package com.mehdi.bankaccount.infrastructure.adapter.persistence.repository;

import com.mehdi.bankaccount.infrastructure.adapter.persistence.model.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OperationRepository extends JpaRepository<OperationEntity, Long> {
    Collection<OperationEntity> findAllByAccountNumber(String accountNumber);
}
