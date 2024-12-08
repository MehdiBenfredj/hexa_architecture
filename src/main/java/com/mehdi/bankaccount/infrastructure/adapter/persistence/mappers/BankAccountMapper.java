package com.mehdi.bankaccount.infrastructure.adapter.persistence.mappers;

import com.mehdi.bankaccount.domain.AccountNumber;
import com.mehdi.bankaccount.domain.BankAccount;
import com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto.BankAccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface BankAccountMapper {

    @Mapping(source = "accountNumber", target = "accountNumber", qualifiedByName = "accountNumberToString")
    BankAccountDto toBankAccountDto(BankAccount bankAccount);

    @Mapping(source = "accountNumber", target = "accountNumber", qualifiedByName = "stringToAccountNumber")
    BankAccount toBankAccount(BankAccountDto bankAccountDto);

    @Named("accountNumberToString")
    default String accountNumberToString(AccountNumber accountNumber) {
        return accountNumber != null ? accountNumber.accountNumber() : null;
    }

    @Named("stringToAccountNumber")
    default AccountNumber stringToAccountNumber(String accountNumber) {
        return accountNumber != null ? new AccountNumber(accountNumber) : null;
    }
}
