package com.example.mybank.dao;

import com.example.mybank.domain.BankStatement;
import com.example.mybank.utils.DatabaseOperations;
import lombok.Setter;

@Setter
public class PersonalBankingDaoImpl implements PersonalBankingDao {

    private DatabaseOperations databaseOperations;

    @Override
    public BankStatement getMiniStatement() {
        return databaseOperations.getMiniStatement();
    }
}
