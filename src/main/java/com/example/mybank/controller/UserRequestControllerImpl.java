package com.example.mybank.controller;

import com.example.mybank.domain.Request;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

@Singleton
@Named("userRequestController")
public class UserRequestControllerImpl implements UserRequestController {

    @Override
    public void submitRequest(Request request) {
    }
}
