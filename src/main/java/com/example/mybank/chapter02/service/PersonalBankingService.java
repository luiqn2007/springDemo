package com.example.mybank.chapter02.service;

import com.example.mybank.chapter02.EmailMessageSender;
import com.example.mybank.chapter02.JmsMessageSender;
import com.example.mybank.chapter02.WebServiceInvoker;

/**
 * 允许客户端检索银行账户对账单
 */
public class PersonalBankingService {

    private JmsMessageSender jmsMessageSender;
    private EmailMessageSender emailMessageSender;
    private WebServiceInvoker webServiceInvoker;

    public PersonalBankingService(JmsMessageSender jmsMessageSender, EmailMessageSender emailMessageSender, WebServiceInvoker webServiceInvoker) {
        this.jmsMessageSender = jmsMessageSender;
        this.emailMessageSender = emailMessageSender;
        this.webServiceInvoker = webServiceInvoker;
    }
}
