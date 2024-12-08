package com.mehdi.bankaccount.application.service;

import com.mehdi.bankaccount.application.port.persistence.ReadBankAccountPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReadAllBankAccountsServiceTest {

    @InjectMocks
    private ReadAllBankAccountsService readAllBankAccountsService;

    @Mock
    ReadBankAccountPort readBankAccountPort;


    @Test
    void shouldAttemptToLoadAllAccounts() {
        readAllBankAccountsService.readAllBankAccounts();
        Mockito.verify(readBankAccountPort).loadAllAccounts();
    }
}