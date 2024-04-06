package com.example.mybank.controller;

import com.example.mybank.core.ServiceTemplate;
import com.example.mybank.domain.Request;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

@Singleton
@Named("userRequestController")
public class UserRequestControllerImpl implements UserRequestController {

    @Inject
    private ServiceTemplate serviceTemplate;

    @Override
    public void submitRequest(Request request) {
        serviceTemplate.getJmsMessageSender();
    }
}
