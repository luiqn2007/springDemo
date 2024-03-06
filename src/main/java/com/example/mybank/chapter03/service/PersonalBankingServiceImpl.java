package com.example.mybank.chapter03.service;

import com.example.mybank.chapter03.BankStatement;
import com.example.mybank.chapter03.dao.PersonalBankingDao;

public class PersonalBankingServiceImpl extends ServiceTemplate implements PersonalBankingService {

    private PersonalBankingDao personalBankingDao;

    public PersonalBankingServiceImpl(PersonalBankingDao personalBankingDao) {
        this.personalBankingDao = personalBankingDao;
    }

    @Override
    public PersonalBankingDao getPersonalBankingDao() {
        return personalBankingDao;
    }

    @Override
    public BankStatement getMiniStatement() {
        return getPersonalBankingDao().getMiniStatement();
    }
}
