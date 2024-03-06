package com.example.mybank.chapter03.controller;

import com.example.mybank.chapter03.service.ServiceTemplate;

public interface UserRequestController {
    ServiceTemplate getServiceTemplate();

    void setServiceTemplate(ServiceTemplate serviceTemplate);
}
