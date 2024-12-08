package com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api;

import com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto.AccountStatementDto;
import com.mehdi.bankaccount.infrastructure.adapter.entrypoint.api.model.dto.BankAccountDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operation")
class OperationRestController {

    private final GetOperationEndpointAdapter getOperationEndpointAdapter;
    private final PostOperationEndpointAdapter postOperationEndpointAdapter;

    public OperationRestController(GetOperationEndpointAdapter getOperationEndpointAdapter, PostOperationEndpointAdapter postOperationEndpointAdapter) {
        this.getOperationEndpointAdapter = getOperationEndpointAdapter;
        this.postOperationEndpointAdapter = postOperationEndpointAdapter;
    }

    @GetMapping("/{accountNumber}")
    public AccountStatementDto getAccountStatement(@PathVariable String accountNumber) {
        return getOperationEndpointAdapter.getAccountStatement(accountNumber);
    }

    @PostMapping("/revert/{operationId}")
    public BankAccountDto revertOperation(@PathVariable Long operationId) {
        return postOperationEndpointAdapter.revertOperation(operationId);
    }

}
