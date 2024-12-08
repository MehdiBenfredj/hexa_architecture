package com.mehdi.bankaccount.application.service;

import com.mehdi.bankaccount.application.port.persistence.ReadOperationPort;
import com.mehdi.bankaccount.application.usecase.DepositToBankAccountUseCase;
import com.mehdi.bankaccount.application.usecase.WithdrawFromBankAccountUseCase;
import com.mehdi.bankaccount.domain.Operation;
import com.mehdi.bankaccount.domain.enums.OperationType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mehdi.bankaccount.utils.DataFaker.fakeOperation;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class RevertOperationServiceTest {

    @InjectMocks
    private RevertOperationService revertOperationService;

    @Mock
    ReadOperationPort readOperationPort;

    @Mock
    DepositToBankAccountUseCase depositToBankAccountUseCase;

    @Mock
    WithdrawFromBankAccountUseCase withdrawFromBankAccountUseCase;


    @Test
    void shouldThrowErrorWhenOperationNotFound() {
        Mockito.when(readOperationPort.loadOperationById(1L)).thenThrow(RuntimeException.class);
        assertThatThrownBy(() -> revertOperationService.revertOperation(1L))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void shouldAttemptToDepositOrWithdraw() {
        Operation fakeOperation = fakeOperation()
                .operationType(OperationType.WITHDRAWAL.getType())
                .build();

        Mockito.when(readOperationPort.loadOperationById(1L)).thenReturn(fakeOperation);

        revertOperationService.revertOperation(1L);

        try {
            Mockito.verify(depositToBankAccountUseCase).deposit(Mockito.any());
        } catch (MockitoException e) {
            Mockito.verify(withdrawFromBankAccountUseCase).withdraw(Mockito.any());
        }

    }
}