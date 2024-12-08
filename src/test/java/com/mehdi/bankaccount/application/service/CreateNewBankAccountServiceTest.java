package com.mehdi.bankaccount.application.service;

import com.mehdi.bankaccount.application.port.persistence.WriteBankAccountPort;
import com.mehdi.bankaccount.domain.BankAccount;
import com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto.CreateBankAccountDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class CreateNewBankAccountServiceTest {

    @InjectMocks
    private CreateNewBankAccountService createNewBankAccountService;

    @Mock
    WriteBankAccountPort writeBankAccountPort;

    @Test
    void shouldThrowError_whenInitialOrOverdraftIsNegative() {
        CreateBankAccountDto createBankAccountDto = new CreateBankAccountDto("depot", 10, -1);
        assertThatThrownBy(() -> createNewBankAccountService.createNewAccount(createBankAccountDto));
        Mockito.verify(writeBankAccountPort, Mockito.never()).createAccount(Mockito.any());
    }

    @Test
    void shouldThrowError_whenInitialBalanceIsNegative() {
        CreateBankAccountDto createBankAccountDto = new CreateBankAccountDto("depot", -6, 10);
        assertThatThrownBy(() -> createNewBankAccountService.createNewAccount(createBankAccountDto));
        Mockito.verify(writeBankAccountPort, Mockito.never()).createAccount(Mockito.any());
    }

    @Test
    void shouldThrowError_whenAccountTypeNotSupported() {
        CreateBankAccountDto createBankAccountDto = new CreateBankAccountDto("x", 10, 10);
        assertThatThrownBy(() -> createNewBankAccountService.createNewAccount(createBankAccountDto));
        Mockito.verify(writeBankAccountPort, Mockito.never()).createAccount(Mockito.any());
    }

    @Test
    void shouldThrowError_whenLivretAndOverdraft() {
        CreateBankAccountDto createBankAccountDto = new CreateBankAccountDto("livret", 10, 10);
        assertThatThrownBy(() -> createNewBankAccountService.createNewAccount(createBankAccountDto));
        Mockito.verify(writeBankAccountPort, Mockito.never()).createAccount(Mockito.any());
    }

    @Test
    void shouldAttemptToCreateAccount() {
        CreateBankAccountDto createBankAccountDto = new CreateBankAccountDto("depot", 10, 100);
        BankAccount createdBankAccount = createNewBankAccountService.createNewAccount(createBankAccountDto);
        Mockito.verify(writeBankAccountPort).createAccount(Mockito.any());
    }
}