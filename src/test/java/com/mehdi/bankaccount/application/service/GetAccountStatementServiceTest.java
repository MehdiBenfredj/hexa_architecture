package com.mehdi.bankaccount.application.service;

import com.mehdi.bankaccount.application.port.persistence.ReadBankAccountPort;
import com.mehdi.bankaccount.application.port.persistence.WriteBankAccountPort;
import com.mehdi.bankaccount.application.port.persistence.WriteOperationPort;
import com.mehdi.bankaccount.domain.AccountNumber;
import com.mehdi.bankaccount.domain.BankAccount;
import com.mehdi.bankaccount.domain.enums.AccountType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mehdi.bankaccount.utils.DataFaker.fakeAccountNumber;
import static com.mehdi.bankaccount.utils.DataFaker.fakeBankAccount;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class GetAccountStatementServiceTest {

    @InjectMocks
    private GetAccountStatementService getAccountStatementService;

    @Mock
    WriteBankAccountPort writeBankAccountPort;

    @Mock
    ReadBankAccountPort readBankAccountPort;

    @Mock
    WriteOperationPort writeOperationPort;

    static BankAccount fakeBankAccount;

    @BeforeAll
    static void setFakeBankAccount() {
        fakeBankAccount = fakeBankAccount()
                .accountNumber(new AccountNumber(fakeAccountNumber().accountNumber()))
                .accountType(AccountType.DEPOT.getType())
                .balance(1000)
                .overdraft(100)
                .build();
    }

    @Test
    void shouldThrowErrorWhenAccountNotFound() {
        Mockito.when(readBankAccountPort.loadAccount(Mockito.any())).thenThrow(RuntimeException.class);
        assertThatThrownBy(() -> getAccountStatementService.getAccountStatement(fakeAccountNumber().accountNumber()))
                .isInstanceOf(RuntimeException.class);
    }
}