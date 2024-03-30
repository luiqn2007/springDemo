package com.example.mybank.service;

import com.example.mybank.base.ServiceTemplate;
import com.example.mybank.domain.BankStatement;
import com.example.mybank.base.EmailMessageSender;
import com.example.mybank.base.JmsMessageSender;
import com.example.mybank.base.WebServiceInvoker;
import com.example.mybank.dao.PersonalBankingDao;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.ConstructorProperties;

@Component("personalBankingService")
public class PersonalBankingServiceImpl extends ServiceTemplate implements PersonalBankingService {

    @Autowired
    private PersonalBankingDao personalBankingDao;

    @Override
    public BankStatement getMiniStatement() {
        return personalBankingDao.getMiniStatement();
    }
}
