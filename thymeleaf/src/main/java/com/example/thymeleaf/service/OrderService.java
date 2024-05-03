package com.example.thymeleaf.service;

import com.example.thymeleaf.domain.Order;
import com.example.thymeleaf.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(int id) {
        return orderRepository.findById(id);
    }

    public List<Order> findByCustomerId(int customerId) {
        return orderRepository.findByCustomerId(customerId);
    }
}
