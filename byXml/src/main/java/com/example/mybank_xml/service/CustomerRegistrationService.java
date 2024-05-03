package com.example.mybank_xml.service;

public interface CustomerRegistrationService {

    void setAccountNumber(String accountNumber);

    void setAddress(String address);

    void setDebitCardNumber(String cardNumber);

    void register();
}
