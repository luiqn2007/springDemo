package com.example.mybank.service;

import com.example.mybank.dao.CustomerRegistrationDao;
import com.example.mybank.domain.CustomerRegistrationDetails;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.springframework.beans.factory.annotation.Qualifier;

@Named("customerRegistrationService")
@Qualifier("service")
public class CustomerRegistrationServiceImpl implements CustomerRegistrationService {

    @Inject
    private CustomerRegistrationDetails customerRegistrationDetails;
    @Inject
    private CustomerRegistrationDao customerRegistrationDao;

    @Override
    public void setAccountNumber(String accountNumber) {
        customerRegistrationDetails.setAccountNumber(accountNumber);
    }

    @Override
    public void setAddress(String address) {
        customerRegistrationDetails.setAddress(address);
    }

    @Override
    public void setDebitCardNumber(String cardNumber) {
        customerRegistrationDetails.setCardNumber(cardNumber);
    }

    @Override
    public void register() {
        customerRegistrationDao.registerCustomer(customerRegistrationDetails);
    }
}
