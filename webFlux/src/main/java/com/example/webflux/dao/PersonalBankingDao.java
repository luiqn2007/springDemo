package com.example.webflux.dao;

import com.example.webflux.domain.BankStatement;

public interface PersonalBankingDao {

    BankStatement getMiniStatement();
}
