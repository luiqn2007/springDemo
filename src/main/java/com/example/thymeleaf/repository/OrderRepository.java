package com.example.thymeleaf.repository;

import com.example.thymeleaf.domain.Customer;
import com.example.thymeleaf.domain.Order;
import com.example.thymeleaf.domain.OrderLine;
import com.example.thymeleaf.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.DateUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class OrderRepository {

    Order[] byId = new Order[3];
    List<Order>[] byCustomer;

    @Autowired
    public OrderRepository(CustomerRepository customerRepository, ProductRepository productRepository) {

        Customer cust1 = customerRepository.findById(0);
        Customer cust4 = customerRepository.findById(3);
        Customer cust6 = customerRepository.findById(5);

        Product prod1 = productRepository.findById(0);
        Product prod2 = productRepository.findById(1);
        Product prod3 = productRepository.findById(2);
        Product prod4 = productRepository.findById(3);

        byId[0] = new Order(0, DateUtils.create(2009, 1, 12, 10, 23), cust4,
                new OrderLine(prod2, 2, "0.99"),
                new OrderLine(prod3, 4, "2.50"),
                new OrderLine(prod4, 1, "15.50"));

        byId[1] = new Order(1, DateUtils.create(2010, 6, 9, 21, 1), cust6,
                new OrderLine(prod1, 5, "3.75"),
                new OrderLine(prod4, 2, "17.99"));

        byId[2] = new Order(2, DateUtils.create(2010, 7, 18, 22, 32), cust1,
                new OrderLine(prod1, 8, "5.99"));

        byCustomer = new List[customerRepository.findAll().size()];
        for (Order order : byId) {
            if (order == null) continue;
            int cid = order.customer().id();
            if (byCustomer[cid] == null) byCustomer[cid] = new ArrayList<>();
            byCustomer[cid].add(order);
        }

        for (int i = 0; i < byCustomer.length; i++) {
            if (byCustomer[i] == null) byCustomer[i] = List.of();
        }
    }

    public List<Order> findAll() {
        return Arrays.asList(byId);
    }

    public Order findById(int id) {
        return byId[id];
    }

    public List<Order> findByCustomerId(int customerId) {
        return byCustomer[customerId];
    }
}
