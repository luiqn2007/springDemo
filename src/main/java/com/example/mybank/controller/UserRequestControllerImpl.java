package com.example.mybank.controller;

import com.example.mybank.base.ServiceTemplate;
import com.example.mybank.domain.Request;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("userRequestController")
public class UserRequestControllerImpl implements UserRequestController {

    @Autowired
    @Qualifier("serviceTemplate")
    private ServiceTemplate serviceTemplate;

    @Override
    public void submitRequest(Request request) {
        serviceTemplate.getJmsMessageSender();
    }
}
