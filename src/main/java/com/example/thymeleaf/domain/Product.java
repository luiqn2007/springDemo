package com.example.thymeleaf.domain;

import java.math.BigDecimal;
import java.util.List;

public record Product(int id, String name, boolean inStock, BigDecimal price, List<Comment> comments) {

    public Product(int id, String name, boolean inStock, BigDecimal price, Comment... comments) {
        this(id, name, inStock, price, List.of(comments));
    }
}
