package com.example.webflux.dao;

import com.example.webflux.domain.CustomerRequestDetails;

public interface CustomerRequestDao {

    void submitRequest(CustomerRequestDetails crd);
}
