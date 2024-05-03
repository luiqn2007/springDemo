package com.example.mybank_xml.dao;

import com.example.mybank_xml.domain.CustomerRequestDetails;

public interface CustomerRequestDao {

    void submitRequest(CustomerRequestDetails crd);
}
