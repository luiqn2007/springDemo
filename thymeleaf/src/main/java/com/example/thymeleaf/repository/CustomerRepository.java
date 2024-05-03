package com.example.thymeleaf.repository;

import com.example.thymeleaf.domain.Customer;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.DateUtils;

import java.util.List;

@Repository
public class CustomerRepository {

    private final Customer[] customersById = new Customer[6];

    public CustomerRepository() {
        customersById[0] = new Customer(0, "James Cucumber", DateUtils.create(2006, 4, 2, 13, 20));
        customersById[1] = new Customer(1, "Anna Lettuce", DateUtils.create(2005, 1, 30, 17, 14));
        customersById[2] = new Customer(2, "Boris Tomato", DateUtils.create(2008, 12, 2, 9, 53));
        customersById[3] = new Customer(3, "Shannon Parsley", DateUtils.create(2009, 3, 24, 10, 45));
        customersById[4] = new Customer(4, "Susan Cheddar", DateUtils.create(2007, 10, 1, 15, 2));
        customersById[5] = new Customer(5, "George Garlic", DateUtils.create(2010, 5, 18, 20, 30));
    }

    public List<Customer> findAll() {
        return List.of(customersById);
    }

    public Customer findById(int id) {
        return customersById[id];
    }
}
