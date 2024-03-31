package com.example.mybank.service;

import com.example.mybank.base.ServiceTemplate;
import com.example.mybank.dao.PersonalBankingDao;
import com.example.mybank.domain.BankStatement;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

@Singleton
@Named("personalBankingService")
public class PersonalBankingServiceImpl extends ServiceTemplate implements PersonalBankingService {

    @Inject
    private PersonalBankingDao personalBankingDao;

    @Override
    public BankStatement getMiniStatement() {
        return personalBankingDao.getMiniStatement();
    }
}
