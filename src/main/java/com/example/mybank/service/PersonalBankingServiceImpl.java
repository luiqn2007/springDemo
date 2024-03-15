package com.example.mybank.service;

import com.example.mybank.BankStatement;
import com.example.mybank.EmailMessageSender;
import com.example.mybank.JmsMessageSender;
import com.example.mybank.WebServiceInvoker;
import com.example.mybank.dao.PersonalBankingDao;
import lombok.Getter;

import java.beans.ConstructorProperties;

@Getter
public class PersonalBankingServiceImpl extends ServiceTemplate implements PersonalBankingService {

    private PersonalBankingDao personalBankingDao;

    @ConstructorProperties({"jmsMessageSender", "emailMessageSender", "webServiceInvoker", "personalBankingDao"})
    public PersonalBankingServiceImpl(JmsMessageSender jmsMessageSender, EmailMessageSender emailMessageSender, WebServiceInvoker webServiceInvoker, PersonalBankingDao personalBankingDao) {
        super(jmsMessageSender, emailMessageSender, webServiceInvoker);
        this.personalBankingDao = personalBankingDao;
    }

    @Override
    public BankStatement getMiniStatement() {
        return getPersonalBankingDao().getMiniStatement();
    }
}
