package com.example.thymeleaf.controller;

import com.example.thymeleaf.domain.Product;
import com.example.thymeleaf.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/thymeleaf/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping("/list")
    public String list(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "product/list";
    }

    @RequestMapping(value = "/comments", params = "productId")
    public String comments(Model model, @Param("productId") int productId) {
        model.addAttribute(productRepository.findById(productId));
        return "product/comments";
    }
}
