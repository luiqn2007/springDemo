package com.example.mybank.chapter03.controller;

public class ControllerFactory {

    public Object getController(String name) {
        return switch (name) {
            case "fixedDepositController" -> new FixedDepositControllerImpl();
            case "userRequestController" -> new UserRequestControllerImpl();
            default -> throw new IllegalArgumentException("Unknown controller type " + name);
        };
    }
}
