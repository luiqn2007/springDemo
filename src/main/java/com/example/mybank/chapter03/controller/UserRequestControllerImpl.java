package com.example.mybank.chapter03.controller;

import com.example.mybank.chapter03.service.ServiceTemplate;

public class UserRequestControllerImpl implements UserRequestController {

    private ServiceTemplate serviceTemplate;

    @Override
    public ServiceTemplate getServiceTemplate() {
        return serviceTemplate;
    }

    @Override
    public void setServiceTemplate(ServiceTemplate serviceTemplate) {
        this.serviceTemplate = serviceTemplate;
    }
}
