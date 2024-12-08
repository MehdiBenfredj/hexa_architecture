package com.mehdi.bankaccount.application.port.persistence;

import com.mehdi.bankaccount.domain.Operation;

import java.util.List;

public interface ReadOperationPort {
    List<Operation> loadOperationsByBankAccount(String accountNumber);
    Operation loadOperationById(Long operationId);

}
