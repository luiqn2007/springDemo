package com.example.thymeleaf.domain;

import java.util.Calendar;
import java.util.Set;

public record Order(int id, Calendar date, Customer customer, Set<OrderLine> orderLines) {

    public Order(int id, Calendar date, Customer customer, OrderLine... orderLines) {
        this(id, date, customer, Set.of(orderLines));
    }
}
