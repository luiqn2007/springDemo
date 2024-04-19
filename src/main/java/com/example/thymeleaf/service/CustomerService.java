package com.example.thymeleaf.service;

import com.example.thymeleaf.domain.Customer;
import com.example.thymeleaf.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(int id) {
        return customerRepository.findById(id);
    }
}
