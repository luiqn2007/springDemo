package com.example.mybank.controller;

public class ControllerFactory {

    public Object getController(String name) {
        return switch (name) {
            case "fixedDepositController" -> new FixedDepositControllerImpl();
            case "userRequestController" -> new UserRequestControllerImpl();
            case "personalBankingController" -> null;
            default -> throw new IllegalArgumentException("Unknown controller type " + name);
        };
    }
}
