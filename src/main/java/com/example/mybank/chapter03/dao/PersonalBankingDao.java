package com.example.mybank.chapter03.dao;

import com.example.mybank.chapter03.BankStatement;
import com.example.mybank.chapter03.utils.DatabaseOperations;

public interface PersonalBankingDao {

    DatabaseOperations getDatabaseOperations();

    void setDatabaseOperations(DatabaseOperations databaseOperations);

    BankStatement getMiniStatement();
}
