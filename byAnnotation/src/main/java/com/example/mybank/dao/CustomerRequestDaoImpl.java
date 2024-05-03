package com.example.mybank.dao;

import com.example.mybank.domain.CustomerRequestDetails;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

@Singleton
@Named("customerRequestDao")
public class CustomerRequestDaoImpl implements CustomerRequestDao {
    @Override
    public void submitRequest(CustomerRequestDetails crd) {
    }
}
