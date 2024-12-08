package com.mehdi.bankaccount.application.port.persistence;

import com.mehdi.bankaccount.domain.Operation;

public interface WriteOperationPort {
    Operation createOperation(Operation operation);
}
