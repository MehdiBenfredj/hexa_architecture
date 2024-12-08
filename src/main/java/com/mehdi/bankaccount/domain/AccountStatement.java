package com.mehdi.bankaccount.domain;

import java.util.List;

public record AccountStatement(String accountNumber, double balance, List<Operation> operationList) {
}
