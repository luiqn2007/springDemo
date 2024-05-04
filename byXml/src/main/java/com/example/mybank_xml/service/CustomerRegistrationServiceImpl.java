package com.example.mybank_xml.service;

import com.example.mybank_xml.dao.CustomerRegistrationDao;
import com.example.mybank_xml.domain.CustomerRegistrationDetails;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;

@Setter
public class CustomerRegistrationServiceImpl implements CustomerRegistrationService {

    private CustomerRegistrationDetails customerRegistrationDetails;
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
