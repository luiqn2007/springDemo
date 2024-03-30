package com.example.mybank.base;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ServiceTemplate {

    @Autowired
    protected JmsMessageSender jmsMessageSender;
    @Autowired
    protected EmailMessageSender emailMessageSender;
    @Autowired
    protected WebServiceInvoker webServiceInvoker;
}
