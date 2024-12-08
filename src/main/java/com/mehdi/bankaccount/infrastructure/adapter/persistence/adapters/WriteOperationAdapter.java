package com.mehdi.bankaccount.infrastructure.adapter.persistence.adapters;

import com.mehdi.bankaccount.application.port.persistence.WriteOperationPort;
import com.mehdi.bankaccount.domain.Operation;
import com.mehdi.bankaccount.infrastructure.adapter.persistence.mappers.OperationEntityMapper;
import com.mehdi.bankaccount.infrastructure.adapter.persistence.model.OperationEntity;
import com.mehdi.bankaccount.infrastructure.adapter.persistence.repository.OperationRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
class WriteOperationAdapter implements WriteOperationPort {

    private final OperationRepository operationRepository;
    private final OperationEntityMapper operationEntityMapper;

    public WriteOperationAdapter(OperationRepository operationRepository, OperationEntityMapper operationEntityMapper) {
        this.operationRepository = operationRepository;
        this.operationEntityMapper = operationEntityMapper;
    }

    @Override
    @Transactional
    public Operation createOperation(Operation operation) {
        OperationEntity operationEntity = operationEntityMapper.toOperationEnity(operation);
        OperationEntity savedOperationEntity = operationRepository.save(operationEntity);
        return operationEntityMapper.toOperation(savedOperationEntity);
    }

}
