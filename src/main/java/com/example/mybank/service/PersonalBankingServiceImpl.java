package com.example.mybank.chapter03.service;

import com.example.mybank.chapter03.BankStatement;
import com.example.mybank.chapter03.EmailMessageSender;
import com.example.mybank.chapter03.JmsMessageSender;
import com.example.mybank.chapter03.WebServiceInvoker;
import com.example.mybank.chapter03.dao.PersonalBankingDao;
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
