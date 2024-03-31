package com.example.mybank.dao;

import com.example.mybank.domain.BankStatement;
import com.example.mybank.utils.DatabaseOperations;

public interface PersonalBankingDao {

    BankStatement getMiniStatement();
}
