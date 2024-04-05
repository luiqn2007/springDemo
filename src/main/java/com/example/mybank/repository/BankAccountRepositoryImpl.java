package com.example.mybank.repository;

import com.example.mybank.domain.BankAccountDetails;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class BankAccountRepositoryImpl implements BankAccountRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void subtractFromAccount(int bankAccountId, float amount) {
        BankAccountDetails bankAccount = entityManager.find(BankAccountDetails.class, bankAccountId);
        if (bankAccount.getBalanceAmount() < amount)
            throw new IllegalArgumentException("Not enough money on account");
        bankAccount.setBalanceAmount(bankAccount.getBalanceAmount() - amount);
        entityManager.merge(bankAccount);
    }
}
