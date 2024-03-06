package com.example.mybank.chapter03.dao;

import com.example.mybank.chapter03.BankStatement;
import com.example.mybank.chapter03.utils.DatabaseOperations;

public class PersonalBankingDaoImpl implements PersonalBankingDao {

    private DatabaseOperations databaseOperations;

    @Override
    public DatabaseOperations getDatabaseOperations() {
        return databaseOperations;
    }

    @Override
    public void setDatabaseOperations(DatabaseOperations databaseOperations) {
        this.databaseOperations = databaseOperations;
    }

    @Override
    public BankStatement getMiniStatement() {
        return null;
    }
}
