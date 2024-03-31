package com.example.mybank.dao;

import com.example.mybank.domain.CustomerRegistrationDetails;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

@Singleton
@Named("customerRegistrationDao")
public class CustomerRegistrationDaoImpl implements CustomerRegistrationDao {

    @Override
    public void registerCustomer(CustomerRegistrationDetails customerRegistrationDetails) {
    }
}
