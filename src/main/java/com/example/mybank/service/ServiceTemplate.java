package com.example.mybank.chapter03.service;

import com.example.mybank.chapter03.EmailMessageSender;
import com.example.mybank.chapter03.JmsMessageSender;
import com.example.mybank.chapter03.WebServiceInvoker;
import lombok.Getter;
import lombok.Setter;

import java.beans.ConstructorProperties;

@Getter
@Setter
public class ServiceTemplate {

    JmsMessageSender jmsMessageSender;
    EmailMessageSender emailMessageSender;
    WebServiceInvoker webServiceInvoker;

    @ConstructorProperties({"jmsMessageSender", "emailMessageSender", "webServiceInvoker"})
    public ServiceTemplate(JmsMessageSender jmsMessageSender, EmailMessageSender emailMessageSender, WebServiceInvoker webServiceInvoker) {
        this.jmsMessageSender = jmsMessageSender;
        this.emailMessageSender = emailMessageSender;
        this.webServiceInvoker = webServiceInvoker;
    }
}
