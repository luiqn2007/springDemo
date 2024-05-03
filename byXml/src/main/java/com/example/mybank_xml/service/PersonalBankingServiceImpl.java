package com.example.mybank_xml.service;

import com.example.mybank_xml.dao.PersonalBankingDao;
import com.example.mybank_xml.domain.BankStatement;
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
