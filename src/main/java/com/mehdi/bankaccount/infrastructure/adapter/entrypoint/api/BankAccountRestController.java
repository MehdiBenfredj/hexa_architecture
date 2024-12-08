package com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api;

import com.mehdi.bankaccount.domain.AccountNumber;
import com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto.BankAccountDto;
import com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto.CreateBankAccountDto;
import com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto.UpdateBankAccountDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/bank_account")
class BankAccountRestController {
    private final PostBankAccountEndpointAdapter postEndpointAdapter;
    private final GetBankAccountEndpointAdapter getEndpointAdapter;

    public BankAccountRestController(PostBankAccountEndpointAdapter postEndpointAdapter, GetBankAccountEndpointAdapter getEndpointAdapter) {
        this.postEndpointAdapter = postEndpointAdapter;
        this.getEndpointAdapter = getEndpointAdapter;
    }


    //CRUD Operations
    //Create
    @PostMapping(
            value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BankAccountDto createBankAccount(@RequestBody CreateBankAccountDto createBankAccountDto) {
        return postEndpointAdapter.createAccount(createBankAccountDto);
    }

    //Read
    @GetMapping("/{accountNumber}")
    public BankAccountDto getBankAccount(@PathVariable String accountNumber) {
        return getEndpointAdapter.getAccount(new AccountNumber(accountNumber));
    }

    @GetMapping
    public Collection<BankAccountDto> getAllBankAccounts() {
        return getEndpointAdapter.getAllAccounts();
    }

    //Update
    @PutMapping(
            value = "/withdraw",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BankAccountDto withdraw(@RequestBody UpdateBankAccountDto updateBankAccountDto) {
        return postEndpointAdapter.withdraw(updateBankAccountDto);
    }

    @PutMapping(
            value = "/deposit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BankAccountDto deposit(@RequestBody UpdateBankAccountDto updateBankAccountDto) {
        return postEndpointAdapter.deposit(updateBankAccountDto);
    }

    @PutMapping(
            value = "/update_overdraft",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BankAccountDto updateOverdraft(@RequestBody UpdateBankAccountDto updateBankAccountDto) {
        return postEndpointAdapter.updateOverdraft(updateBankAccountDto);
    }
    //Delete
    @DeleteMapping("/{accountNumber}")
    public void deleteBankAccount(@PathVariable String accountNumber) {
        postEndpointAdapter.deleteAccount(accountNumber);
    }
}
