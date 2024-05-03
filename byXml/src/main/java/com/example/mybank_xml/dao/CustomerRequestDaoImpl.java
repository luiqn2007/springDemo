package com.example.mybank_xml.dao;

import com.example.mybank_xml.domain.CustomerRequestDetails;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

@Singleton
@Named("customerRequestDao")
public class CustomerRequestDaoImpl implements CustomerRequestDao {
    @Override
    public void submitRequest(CustomerRequestDetails crd) {
    }
}
