package com.example.mybank_xml.dao;

import com.example.mybank_xml.domain.BankStatement;

import java.util.Date;

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
