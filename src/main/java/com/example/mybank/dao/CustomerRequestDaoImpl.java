package com.example.mybank.dao;

import com.example.mybank.domain.CustomerRequestDetails;
import org.springframework.stereotype.Repository;

@Repository("customerRequestDao")
public class CustomerRequestDaoImpl implements CustomerRequestDao {
    @Override
    public void submitRequest(CustomerRequestDetails crd) {

    }
}
