package com.example.mybank.dao;

import com.example.mybank.domain.CustomerRegistrationDetails;

public interface CustomerRegistrationDao {

    void registerCustomer(CustomerRegistrationDetails customerRegistrationDetails);
}
