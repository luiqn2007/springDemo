package com.example.mybank.mongodb_repository;

public interface BankAccountRepositoryCustom {

    void subtractFromAccount(String bankAccountId, float amount);
}
