package com.example.mybank.service;

import com.example.mybank.dao.CustomerRequestDao;
import com.example.mybank.domain.CustomerRequestDetails;
import lombok.AllArgsConstructor;

import java.beans.ConstructorProperties;

@AllArgsConstructor(onConstructor_ = @ConstructorProperties({"customerRequestDao"}))
public abstract class CustomerRequestServiceImpl2 implements CustomerRequestService {

    private final CustomerRequestDao customerRequestDao;

    public abstract CustomerRequestDetails getCustomerDetails();

    @Override
    public void submitRequest(String requestType, String requestDescription) {
        CustomerRequestDetails details = getCustomerDetails();
        details.setType(requestType);
        details.setDescription(requestDescription);
        customerRequestDao.submitRequest(details);
    }
}
