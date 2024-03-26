package com.example.mybank.controller;

import com.example.mybank.base.ServiceTemplate;
import com.example.mybank.domain.Request;

public interface UserRequestController {

    void setServiceTemplate(ServiceTemplate serviceTemplate);

    void submitRequest(Request request);
}
