package com.example.mybank.service;

import com.example.mybank.dao.CustomerRegistrationDao;
import com.example.mybank.domain.CustomerRegistrationDetails;

import java.beans.ConstructorProperties;

public class CustomerRegistrationServiceImpl implements CustomerRegistrationService {

    private CustomerRegistrationDetails customerRegistrationDetails;
    private CustomerRegistrationDao customerRegistrationDao;

    @ConstructorProperties({"customerRegistrationDetails", "customerRegistrationDao"})
    public CustomerRegistrationServiceImpl(CustomerRegistrationDetails customerRegistrationDetails, CustomerRegistrationDao customerRegistrationDao) {
        this.customerRegistrationDetails = customerRegistrationDetails;
        this.customerRegistrationDao = customerRegistrationDao;
    }
}
