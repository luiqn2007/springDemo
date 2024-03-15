package com.example.mybank.dao;

import com.example.mybank.beans.BankStatement;
import com.example.mybank.utils.DatabaseOperations;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonalBankingDaoImpl implements PersonalBankingDao {

    private DatabaseOperations databaseOperations;

    @Override
    public BankStatement getMiniStatement() {
        return null;
    }
}
