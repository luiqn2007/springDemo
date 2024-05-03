package com.example.mybank_xml.service;

import com.example.mybank_xml.dao.CustomerRequestDao;
import com.example.mybank_xml.domain.CustomerRequestDetails;

import java.beans.ConstructorProperties;
import java.util.Calendar;

public abstract class CustomerRequestServiceImpl implements CustomerRequestService {

    private final CustomerRequestDao customerRequestDao;

    public abstract CustomerRequestDetails getCustomerDetails();

    @ConstructorProperties("customerRequestDao")
    public CustomerRequestServiceImpl(CustomerRequestDao customerRequestDao) {
        this.customerRequestDao = customerRequestDao;
    }

    @Override
    public Calendar submitRequest(String type, String description, Calendar accountSinceDate) {
        CustomerRequestDetails details = getCustomerDetails();
        details.setType(type);
        details.setDescription(description);
        details.setSubmissionDate(accountSinceDate.getTime());
        customerRequestDao.submitRequest(details);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        return cal;
    }
}
