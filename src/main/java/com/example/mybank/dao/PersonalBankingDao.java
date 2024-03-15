package com.example.mybank.dao;

import com.example.mybank.BankStatement;
import com.example.mybank.utils.DatabaseOperations;

public interface PersonalBankingDao {

    DatabaseOperations getDatabaseOperations();

    void setDatabaseOperations(DatabaseOperations databaseOperations);

    BankStatement getMiniStatement();
}
