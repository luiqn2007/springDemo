package com.example.mybank.service;

import com.example.mybank.dao.CustomerRequestDao;
import com.example.mybank.domain.CustomerRequestDetails;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.beans.ConstructorProperties;

public class CustomerRequestServiceImpl implements CustomerRequestService, ApplicationContextAware {

    private final CustomerRequestDao customerRequestDao;
    @Setter
    private ApplicationContext applicationContext;

    @ConstructorProperties("consumerRequestDao")
    public CustomerRequestServiceImpl(CustomerRequestDao customerRequestDao) {
        this.customerRequestDao = customerRequestDao;
    }

    @Override
    public void submitRequest(String requestType, String requestDescription) {
        CustomerRequestDetails details = applicationContext.getBean(CustomerRequestDetails.class);
        details.setRequestType(requestType);
        details.setRequestDescription(requestDescription);
        customerRequestDao.submitRequest(details);
    }
}
