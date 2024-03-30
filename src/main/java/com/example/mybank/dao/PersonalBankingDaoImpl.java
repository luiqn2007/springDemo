package com.example.mybank.dao;

import com.example.mybank.domain.BankStatement;
import com.example.mybank.utils.DatabaseOperations;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("personalBankingDao")
@Setter
public class PersonalBankingDaoImpl implements PersonalBankingDao {

    @Autowired
    private DatabaseOperations databaseOperations;

    @Override
    public BankStatement getMiniStatement() {
        return databaseOperations.getMiniStatement();
    }
}
