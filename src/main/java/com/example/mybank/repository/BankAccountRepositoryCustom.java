package com.example.mybank.repository;

public interface BankAccountRepositoryCustom {

    void subtractFromAccount(int bankAccountId, float amount);

    boolean isDuplicateAccount(int bankAccountId);
}
