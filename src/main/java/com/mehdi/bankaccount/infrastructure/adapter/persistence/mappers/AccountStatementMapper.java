package com.mehdi.bankaccount.infrastructure.adapter.persistence.mappers;

import com.mehdi.bankaccount.domain.AccountStatement;
import com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto.AccountStatementDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface AccountStatementMapper {
    AccountStatementDto toAccountStatementDto(AccountStatement accountStatement);
}
