package com.example.mybank.chapter03.service;

import com.example.mybank.chapter03.EmailMessageSender;
import com.example.mybank.chapter03.JmsMessageSender;
import com.example.mybank.chapter03.WebServiceInvoker;

public class ServiceTemplate {

    JmsMessageSender jmsMessageSender;
    EmailMessageSender emailMessageSender;
    WebServiceInvoker webServiceInvoker;

    public JmsMessageSender getJmsMessageSender() {
        return jmsMessageSender;
    }

    public void setJmsMessageSender(JmsMessageSender jmsMessageSender) {
        this.jmsMessageSender = jmsMessageSender;
    }

    public EmailMessageSender getEmailMessageSender() {
        return emailMessageSender;
    }

    public void setEmailMessageSender(EmailMessageSender emailMessageSender) {
        this.emailMessageSender = emailMessageSender;
    }

    public WebServiceInvoker getWebServiceInvoker() {
        return webServiceInvoker;
    }

    public void setWebServiceInvoker(WebServiceInvoker webServiceInvoker) {
        this.webServiceInvoker = webServiceInvoker;
    }
}
