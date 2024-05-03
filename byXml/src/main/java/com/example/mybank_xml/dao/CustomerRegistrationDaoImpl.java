package com.example.mybank_xml.dao;

import com.example.mybank_xml.domain.CustomerRegistrationDetails;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import lombok.Setter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.HashMap;
import java.util.Map;

@Setter
@Singleton
@Named("customerRegistrationDao")
public class CustomerRegistrationDaoImpl implements CustomerRegistrationDao {

    private static final String SQL_INSERT_CUSTOMER_REGISTRATION =
            "insert into customer_registration_details(ACCOUNT_NUMBER, ADDRESS, CARD_NUMBER) " +
            "value (:account, :address, :number)";

    private TransactionTemplate transactionTemplate;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void registerCustomer(CustomerRegistrationDetails customerRegistrationDetails) {
        transactionTemplate.executeWithoutResult(status -> {
            Map<String, Object> arguments = new HashMap<>();
            arguments.put("account", customerRegistrationDetails.getAccountNumber());
            arguments.put("address", customerRegistrationDetails.getAddress());
            arguments.put("number", customerRegistrationDetails.getCardNumber());
            jdbcTemplate.update(SQL_INSERT_CUSTOMER_REGISTRATION, arguments);
        });
    }
}
