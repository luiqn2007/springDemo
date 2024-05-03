package com.example.mybank.dao;

import com.example.mybank.domain.CustomerRequestDetails;

public interface CustomerRequestDao {

    void submitRequest(CustomerRequestDetails crd);
}
