package com.example.mybank.chapter03.dao;

import com.example.mybank.chapter03.BankStatement;
import com.example.mybank.chapter03.utils.DatabaseOperations;
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
