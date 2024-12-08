package com.mehdi.bankaccount.infrastructure.adapter.persistence.mappers;

import com.mehdi.bankaccount.domain.Operation;
import com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto.OperationDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface OperationMapper {
    OperationDto toOperationDto(Operation operation);
}
