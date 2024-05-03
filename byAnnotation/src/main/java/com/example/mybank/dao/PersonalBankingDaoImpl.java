package com.example.mybank.dao;

import com.example.mybank.domain.BankStatement;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import lombok.Setter;

import java.util.Date;

@Setter
@Singleton
@Named("personalBankingDao")
public class PersonalBankingDaoImpl implements PersonalBankingDao {

    @Override
    public BankStatement getMiniStatement() {
        return BankStatement.builder()
                .amount(100)
                .referenceNumber("Ref. no. 1")
                .transactionDate(new Date())
                .transactionType("credit")
                .build();
    }
}
