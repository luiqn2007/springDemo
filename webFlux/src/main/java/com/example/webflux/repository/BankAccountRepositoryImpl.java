package com.example.webflux.repository;

import com.example.webflux.domain.BankAccountDetails;
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
        entityManager.persist(bankAccount);
    }

    @Override
    public boolean isDuplicateAccount(int bankAccountId) {
        String hql = "select count(*) from BankAccountDetails where BankAccountDetails.accountId = " + bankAccountId;
        return (Integer) entityManager.createQuery(hql).getSingleResult() > 0;
    }
}
