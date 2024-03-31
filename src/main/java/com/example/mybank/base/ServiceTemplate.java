package com.example.mybank.base;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import lombok.Getter;

@Getter
@Named
@Singleton
public class ServiceTemplate {

    @Inject
    protected JmsMessageSender jmsMessageSender;
    @Inject
    protected EmailMessageSender emailMessageSender;
    @Inject
    protected WebServiceInvoker webServiceInvoker;
}
