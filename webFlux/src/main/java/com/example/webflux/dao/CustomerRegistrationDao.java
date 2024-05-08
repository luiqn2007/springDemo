package com.example.webflux.dao;

import com.example.webflux.domain.CustomerRegistrationDetails;

public interface CustomerRegistrationDao {

    void registerCustomer(CustomerRegistrationDetails customerRegistrationDetails);
}
