package com.example.mybank_xml.service;

import com.example.mybank_xml.dao.CustomerRegistrationDao;
import com.example.mybank_xml.domain.CustomerRegistrationDetails;

public interface CustomerRegistrationService {

    void setAccountNumber(String accountNumber);

    void setAddress(String address);

    void setDebitCardNumber(String cardNumber);

    void register();

    void setCustomerRegistrationDetails(CustomerRegistrationDetails customerRegistrationDetails);

    void setCustomerRegistrationDao(CustomerRegistrationDao customerRegistrationDao);
}
