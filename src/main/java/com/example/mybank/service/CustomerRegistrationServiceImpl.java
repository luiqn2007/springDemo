package com.example.mybank.service;

import com.example.mybank.dao.CustomerRegistrationDao;
import com.example.mybank.domain.CustomerRegistrationDetails;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.beans.ConstructorProperties;

@AllArgsConstructor(onConstructor_ = @ConstructorProperties({"customerRegistrationDetails", "customerRegistrationDao"}))
public class CustomerRegistrationServiceImpl implements CustomerRegistrationService {

    private final CustomerRegistrationDetails customerRegistrationDetails;
    private final CustomerRegistrationDao customerRegistrationDao;

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
