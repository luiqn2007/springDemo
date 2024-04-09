package com.example.mybank.dao;

import com.example.mybank.domain.BankAccountDetails;

public interface BankAccountDao {

    void createBankAccount(BankAccountDetails bankAccountDetails);

    BankAccountDetails getBankAccount(int bankAccountId);

    void subtractAmount(int bankAccountId, float amount);

    boolean isDuplicateAccount(int bankAccountId);
}
