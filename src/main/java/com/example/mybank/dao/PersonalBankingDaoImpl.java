package com.example.mybank.dao;

import com.example.mybank.domain.BankStatement;
import com.example.mybank.utils.DatabaseOperations;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import lombok.Setter;

@Setter
@Singleton
@Named("personalBankingDao")
public class PersonalBankingDaoImpl implements PersonalBankingDao {

    @Inject
    private DatabaseOperations databaseOperations;

    @Override
    public BankStatement getMiniStatement() {
        return databaseOperations.getMiniStatement();
    }
}
