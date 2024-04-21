package com.example.thymeleaf.controller;

import com.example.thymeleaf.domain.Order;
import com.example.thymeleaf.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/thymeleaf/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping("/list")
    public String list(Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "order/list";
    }

    @RequestMapping(value = "/details", params = "orderId")
    public String details(Model model, @Param("orderId") int orderId) {
        model.addAttribute(orderRepository.findById(orderId));
        return "order/details";
    }
}
