package com.example.mybank.base;

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
