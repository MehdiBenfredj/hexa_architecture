package com.mehdi.bankaccount.application.service;

import com.mehdi.bankaccount.application.port.persistence.ReadBankAccountPort;
import com.mehdi.bankaccount.application.port.persistence.WriteBankAccountPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mehdi.bankaccount.utils.DataFaker.fakeAccountNumber;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class DeleteBankAccountServiceTest {

    @InjectMocks
    private DeleteBankAccountService deleteBankAccountService;

    @Mock
    WriteBankAccountPort writeBankAccountPort;

    @Mock
    ReadBankAccountPort readBankAccountPort;

    @Test
    void shouldThrowError_whenAccountNumberIsNotFound() {
        Mockito.when(readBankAccountPort.loadAccount(fakeAccountNumber())).thenThrow(RuntimeException.class);

        assertThatThrownBy(() -> deleteBankAccountService.deleteBankAccountById(fakeAccountNumber()))
                .isInstanceOf(RuntimeException.class);

        Mockito.verify(writeBankAccountPort, Mockito.never()).deleteBankAccount(Mockito.any());
    }

    @Test
    void shouldAttemptToDeleteBankAccount() {
        Mockito.when(readBankAccountPort.loadAccount(fakeAccountNumber())).thenReturn(null);

        deleteBankAccountService.deleteBankAccountById(fakeAccountNumber());

        Mockito.verify(writeBankAccountPort).deleteBankAccount(Mockito.any());
    }
}