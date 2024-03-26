package com.example.mybank.controller;

import com.example.mybank.base.ServiceTemplate;
import com.example.mybank.domain.Request;
import lombok.Setter;

public class UserRequestControllerImpl implements UserRequestController {

    @Setter
    private ServiceTemplate serviceTemplate;

    @Override
    public void submitRequest(Request request) {
        serviceTemplate.getJmsMessageSender();
    }
}
