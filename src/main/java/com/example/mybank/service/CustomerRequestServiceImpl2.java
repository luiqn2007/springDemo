package com.example.mybank.service;

import com.example.mybank.dao.CustomerRequestDao;
import com.example.mybank.domain.CustomerRequestDetails;

import java.beans.ConstructorProperties;

public abstract class CustomerRequestServiceImpl2 implements CustomerRequestService {

    private final CustomerRequestDao customerRequestDao;

    @ConstructorProperties("customerRequestDao")
    public CustomerRequestServiceImpl2(CustomerRequestDao customerRequestDao) {
        this.customerRequestDao = customerRequestDao;
    }

    public abstract CustomerRequestDetails getCustomerDetails();

    @Override
    public void submitRequest(String requestType, String requestDescription) {
        CustomerRequestDetails details = getCustomerDetails();
        details.setRequestType(requestType);
        details.setRequestDescription(requestDescription);
        customerRequestDao.submitRequest(details);
    }
}
