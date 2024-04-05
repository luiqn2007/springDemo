package com.example.mybank.service;

import com.example.mybank.mongodb_domain.MongoBankAccountDetails;
import com.example.mybank.mongodb_domain.MongoFixedDepositDetails;
import com.example.mybank.mongodb_repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service("accountService")
@Profile("mongodb")
public class BankAccountServiceMongoDBImpl {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public void createAccount(MongoBankAccountDetails bankAccountDetails) {
        bankAccountRepository.save(bankAccountDetails);
    }

    public MongoBankAccountDetails getBankAccount(String id) {
        return bankAccountRepository.findById(id).orElseThrow();
    }

    public void subtractAmount(String bankAccountId, float amount) {
        bankAccountRepository.subtractFromAccount(bankAccountId, amount);
    }

    public List<MongoFixedDepositDetails> getHighValueFds(int minValue) {
        return Collections.emptyList();
    }
}
