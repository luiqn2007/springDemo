package com.example.mybank.service;

import com.example.mybank.dao.CustomerRegistrationDao;
import com.example.mybank.domain.CustomerRegistrationDetails;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.ConstructorProperties;

@Component
public class CustomerRegistrationServiceImpl implements CustomerRegistrationService {

    @Autowired
    private CustomerRegistrationDetails customerRegistrationDetails;
    @Autowired
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
