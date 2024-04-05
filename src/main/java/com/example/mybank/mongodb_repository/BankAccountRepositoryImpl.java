package com.example.mybank.mongodb_repository;

import com.example.mybank.mongodb_domain.MongoBankAccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.Objects;

public class BankAccountRepositoryImpl implements BankAccountRepositoryCustom {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void subtractFromAccount(String bankAccountId, float amount) {
        MongoBankAccountDetails accountDetails = mongoOperations.findById(bankAccountId, MongoBankAccountDetails.class);
        Objects.requireNonNull(accountDetails, "Account not found");
        if (accountDetails.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        accountDetails.setBalance(accountDetails.getBalance() - amount);
        mongoOperations.save(accountDetails);
    }
}
