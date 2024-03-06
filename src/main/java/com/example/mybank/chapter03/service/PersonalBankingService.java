package com.example.mybank.chapter03.service;

import com.example.mybank.chapter03.BankStatement;
import com.example.mybank.chapter03.dao.PersonalBankingDao;

/**
 * 允许客户端检索银行账户对账单
 */
public interface PersonalBankingService {

    PersonalBankingDao getPersonalBankingDao();

    BankStatement getMiniStatement();
}
