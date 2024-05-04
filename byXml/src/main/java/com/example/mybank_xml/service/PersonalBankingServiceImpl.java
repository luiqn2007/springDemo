package com.example.mybank_xml.service;

import com.example.mybank_xml.dao.PersonalBankingDao;
import com.example.mybank_xml.domain.BankStatement;
import lombok.Getter;
import lombok.Setter;

@Setter
public class PersonalBankingServiceImpl implements PersonalBankingService {

    private PersonalBankingDao personalBankingDao;

    @Override
    public BankStatement getMiniStatement() {
        return personalBankingDao.getMiniStatement();
    }
}
