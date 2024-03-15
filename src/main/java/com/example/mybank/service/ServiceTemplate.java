package com.example.mybank.service;

import com.example.mybank.EmailMessageSender;
import com.example.mybank.JmsMessageSender;
import com.example.mybank.WebServiceInvoker;
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
