package com.mehdi.bankaccount.infrastructure.adapter.persistence.mappers;

import com.mehdi.bankaccount.domain.AccountNumber;
import com.mehdi.bankaccount.domain.BankAccount;
import com.mehdi.bankaccount.infrastructure.adapter.persistence.model.BankAccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
@Component
public interface BankAccountEntityMapper {


        @Mapping(source = "accountNumber", target = "accountNumber", qualifiedByName = "accountNumberToString")
        BankAccountEntity toBankAccountEntity(BankAccount bankAccount);

        @Mapping(source = "accountNumber", target = "accountNumber", qualifiedByName = "stringToAccountNumber")
        BankAccount toBankAccount(BankAccountEntity bankAccountEntity);

        @Named("accountNumberToString")
        default String accountNumberToString(AccountNumber accountNumber) {
            return accountNumber != null ? accountNumber.accountNumber() : null;
        }

        @Named("stringToAccountNumber")
        default AccountNumber stringToAccountNumber(String accountNumber) {
            return accountNumber != null ? new AccountNumber(accountNumber) : null;
        }
}
