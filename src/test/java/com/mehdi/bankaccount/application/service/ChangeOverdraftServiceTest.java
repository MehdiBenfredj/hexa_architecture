package com.mehdi.bankaccount.application.service;

import com.mehdi.bankaccount.application.port.persistence.ReadBankAccountPort;
import com.mehdi.bankaccount.application.port.persistence.WriteBankAccountPort;
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
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class ChangeOverdraftServiceTest {

    @InjectMocks
    private ChangeOverdraftService changeOverdraftService;

    @Mock
    WriteBankAccountPort writeBankAccountPort;

    @Mock
    ReadBankAccountPort readBankAccountPort;

    static BankAccount fakeBankAccount;

    @BeforeAll
    static void setFakeBankAccount() {
        fakeBankAccount = fakeBankAccount()
                .accountNumber( new AccountNumber(fakeAccountNumber().accountNumber()))
                .accountType(AccountType.DEPOT.getType())
                .balance(1000)
                .overdraft(100)
                .build();
    }





    @Test
    void shouldThrowError_whenAmountIsNegative() {
        UpdateBankAccountDto fakeUpdateBankAccountDto = new UpdateBankAccountDto( fakeAccountNumber().accountNumber(), -1 );

        assertThatThrownBy(() -> changeOverdraftService.changeOverdraft(fakeUpdateBankAccountDto))
                .isInstanceOf(RuntimeException.class);

        Mockito.verify(readBankAccountPort, Mockito.never()).loadAllAccounts();
    }

    @Test
    void shouldThrowError_whenLivret() {
        UpdateBankAccountDto fakeUpdateBankAccountDto = new UpdateBankAccountDto( fakeAccountNumber().accountNumber(), 10 );
        Mockito.when(readBankAccountPort.loadAccount(Mockito.any())).thenReturn(fakeBankAccount);

        fakeBankAccount.setAccountType(AccountType.LIVRET.getType());

        assertThatThrownBy(() -> changeOverdraftService.changeOverdraft(fakeUpdateBankAccountDto))
                .isInstanceOf(RuntimeException.class);

        Mockito.verify(readBankAccountPort, Mockito.never()).loadAllAccounts();
    }

    @Test
    void shouldAttemptToUpdateBankAccount() {
        UpdateBankAccountDto fakeUpdateBankAccountDto = new UpdateBankAccountDto( fakeAccountNumber().accountNumber(), 10 );

        Mockito.when(readBankAccountPort.loadAccount(Mockito.any())).thenReturn(fakeBankAccount);

        BankAccount updatedBankAccount = changeOverdraftService.changeOverdraft(fakeUpdateBankAccountDto);

        assertThat(updatedBankAccount.getOverdraft()).isEqualTo(10);

        Mockito.verify(readBankAccountPort, Mockito.never()).loadAllAccounts();
        Mockito.verify(writeBankAccountPort).updateBankAccount(Mockito.any());
    }
}