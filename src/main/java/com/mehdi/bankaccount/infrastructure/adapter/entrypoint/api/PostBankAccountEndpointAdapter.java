package com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api;

import com.mehdi.bankaccount.application.port.entrypoint.api.PostBankAccountEndpointPort;
import com.mehdi.bankaccount.application.usecase.*;
import com.mehdi.bankaccount.domain.AccountNumber;
import com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto.BankAccountDto;
import com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto.CreateBankAccountDto;
import com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto.UpdateBankAccountDto;
import com.mehdi.bankaccount.infrastructure.adapter.persistence.mappers.BankAccountMapper;
import org.springframework.stereotype.Component;

@Component
class PostBankAccountEndpointAdapter implements PostBankAccountEndpointPort {
    private final DeleteBankAccountUseCase deleteBankAccountUseCase;
    private final CreateNewBankAccountUseCase createNewBankAccountUseCase;
    private final DepositToBankAccountUseCase depositToBankAccountUseCase;
    private final WithdrawFromBankAccountUseCase withdrawFromBankAccountUseCase;
    private final ChangeOverdraftUseCase changeOverdraftUseCase;
    private final BankAccountMapper bankAccountMapper;

    public PostBankAccountEndpointAdapter(DeleteBankAccountUseCase deleteBankAccountUseCase, CreateNewBankAccountUseCase createNewBankAccountUseCase, DepositToBankAccountUseCase depositToBankAccountUseCase, WithdrawFromBankAccountUseCase withdrawFromBankAccountUseCase, ChangeOverdraftUseCase changeOverdraftUseCase, BankAccountMapper bankAccountMapper) {
        this.deleteBankAccountUseCase = deleteBankAccountUseCase;
        this.createNewBankAccountUseCase = createNewBankAccountUseCase;
        this.depositToBankAccountUseCase = depositToBankAccountUseCase;
        this.withdrawFromBankAccountUseCase = withdrawFromBankAccountUseCase;
        this.changeOverdraftUseCase = changeOverdraftUseCase;
        this.bankAccountMapper = bankAccountMapper;
    }

    @Override
    public BankAccountDto createAccount(CreateBankAccountDto createBankAccountDto) {
        return bankAccountMapper.toBankAccountDto( createNewBankAccountUseCase.createNewAccount(createBankAccountDto) );
    }

    @Override
    public BankAccountDto deposit(UpdateBankAccountDto updateBankAccountDto) {
        return bankAccountMapper.toBankAccountDto( depositToBankAccountUseCase.deposit(updateBankAccountDto) );
    }

    @Override
    public BankAccountDto withdraw(UpdateBankAccountDto updateBankAccountDto) {
        return bankAccountMapper.toBankAccountDto( withdrawFromBankAccountUseCase.withdraw(updateBankAccountDto) );
    }

    public BankAccountDto updateOverdraft(UpdateBankAccountDto updateBankAccountDto) {
        return bankAccountMapper.toBankAccountDto( changeOverdraftUseCase.changeOverdraft(updateBankAccountDto) );
    }

    @Override
    public void deleteAccount(String accountNumber) {
        deleteBankAccountUseCase.deleteBankAccountById(new AccountNumber(accountNumber));
    }

}
