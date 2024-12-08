package com.mehdi.bankaccount.utils;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.mehdi.bankaccount.domain.AccountNumber;
import com.mehdi.bankaccount.domain.BankAccount;
import com.mehdi.bankaccount.domain.Operation;

import java.util.Locale;

public class DataFaker {

    private static final Faker FAKER = new Faker();

    private static final FakeValuesService FAKE_VALUES_SERVICE = new FakeValuesService(Locale.FRANCE, new RandomService());

    public static BankAccount.BankAccountBuilder fakeBankAccount() {
        return BankAccount.BankAccountBuilder.BankAccount();
    }

    public static AccountNumber fakeAccountNumber() {
        return new AccountNumber(FAKER.expression("9231d146-6234-4df6-ab97-709cef50a4fe"));
    }

    public static Operation.OperationBuilder fakeOperation() {
        return new Operation.OperationBuilder();
    }



}
