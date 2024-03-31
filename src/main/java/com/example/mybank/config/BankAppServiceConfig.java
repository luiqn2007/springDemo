package com.example.mybank.config;

import com.example.mybank.dao.CustomerRequestDao;
import com.example.mybank.domain.CustomerRequestDetails;
import com.example.mybank.service.CustomerRequestService;
import com.example.mybank.service.CustomerRequestServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BankAppServiceConfig {

    @Bean
    public CustomerRequestService customerRequestService(CustomerRequestDao customerRequestDao) {
        return new CustomerRequestServiceImpl(customerRequestDao) {

            @Override
            public CustomerRequestDetails getCustomerDetails() {
                return new CustomerRequestDetails();
            }
        };
    }
}
