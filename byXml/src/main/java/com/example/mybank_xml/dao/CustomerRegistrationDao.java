package com.example.mybank_xml.dao;

import com.example.mybank_xml.domain.CustomerRegistrationDetails;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;

public interface CustomerRegistrationDao {

    void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate);

    void setTransactionTemplate(TransactionTemplate transactionTemplate);

    void registerCustomer(CustomerRegistrationDetails customerRegistrationDetails);
}
