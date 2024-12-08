package com.mehdi.bankaccount.infrastructure.adapter.persistence.adapters;

import com.mehdi.bankaccount.application.port.persistence.ReadOperationPort;
import com.mehdi.bankaccount.domain.Operation;
import com.mehdi.bankaccount.infrastructure.adapter.persistence.mappers.OperationEntityMapper;
import com.mehdi.bankaccount.infrastructure.adapter.persistence.repository.OperationRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
class ReadOperationAdapter implements ReadOperationPort {
    private final OperationRepository operationRepository;
    private final OperationEntityMapper operationEntityMapper;

    public ReadOperationAdapter(OperationRepository operationRepository, OperationEntityMapper operationEntityMapper) {
        this.operationRepository = operationRepository;
        this.operationEntityMapper = operationEntityMapper;
    }

    @Override
    public List<Operation> loadOperationsByBankAccount(String accountNumber) {
        return operationRepository.findAllByAccountNumber(accountNumber).stream()
                .map(operationEntityMapper::toOperation)
                .collect(Collectors.toList());
    }

    @Override
    public Operation loadOperationById(Long operationId) {
        return operationEntityMapper.toOperation( operationRepository.findById(operationId)
                .orElseThrow(() -> new RuntimeException("No such operation")) );
    }
}
