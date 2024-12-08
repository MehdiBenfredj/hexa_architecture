package com.mehdi.bankaccount.infrastructure.adapter.persistence.mappers;

import com.mehdi.bankaccount.domain.Operation;
import com.mehdi.bankaccount.infrastructure.adapter.persistence.model.OperationEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface OperationEntityMapper {
    Operation toOperation(OperationEntity operation);
    OperationEntity toOperationEnity(Operation operation);
}
