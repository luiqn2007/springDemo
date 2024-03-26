package com.example.mybank.dao;

import com.example.mybank.domain.BankStatement;
import com.example.mybank.utils.DatabaseOperations;

public interface PersonalBankingDao {

    void setDatabaseOperations(DatabaseOperations databaseOperations);

    BankStatement getMiniStatement();
}
