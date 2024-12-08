package com.mehdi.bankaccount.application.service;

import com.mehdi.bankaccount.application.port.persistence.ReadBankAccountPort;
import com.mehdi.bankaccount.application.port.persistence.WriteBankAccountPort;
import com.mehdi.bankaccount.application.port.persistence.WriteOperationPort;
import com.mehdi.bankaccount.domain.AccountNumber;
import com.mehdi.bankaccount.domain.BankAccount;
import com.mehdi.bankaccount.domain.enums.AccountType;
import com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto.UpdateBankAccountDto;
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
class DepositToBankAccountServiceTest {

    @InjectMocks
    private DepositToBankAccountService depositToBankAccountService;

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
    void shouldThrowError_whenAmountIsNegative() {
        UpdateBankAccountDto fakeUpdateBankAccountDto = new UpdateBankAccountDto( fakeAccountNumber().accountNumber(), -1 );

        assertThatThrownBy(() -> depositToBankAccountService.deposit(fakeUpdateBankAccountDto))
                .isInstanceOf(RuntimeException.class);

        Mockito.verify(readBankAccountPort, Mockito.never()).loadAccount(fakeAccountNumber());
    }

    @Test
    void shouldAttemptToUpdateBankAccountAndCreateOperation() {
        UpdateBankAccountDto fakeUpdateBankAccountDto = new UpdateBankAccountDto( fakeAccountNumber().accountNumber(), 10 );
        Mockito.when(readBankAccountPort.loadAccount(Mockito.any())).thenReturn(fakeBankAccount);

        depositToBankAccountService.deposit(fakeUpdateBankAccountDto);

        Mockito.verify(writeBankAccountPort).updateBankAccount(Mockito.any());
        Mockito.verify(writeOperationPort).createOperation(Mockito.any());
    }
}