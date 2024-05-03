package com.example.mybank.service;

import com.example.mybank.dao.PersonalBankingDao;
import com.example.mybank.domain.BankStatement;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import org.springframework.beans.factory.annotation.Qualifier;

@Singleton
@Named("personalBankingService")
@Qualifier("service")
public class PersonalBankingServiceImpl implements PersonalBankingService {

    @Inject
    private PersonalBankingDao personalBankingDao;

    @Override
    public BankStatement getMiniStatement() {
        return personalBankingDao.getMiniStatement();
    }
}
