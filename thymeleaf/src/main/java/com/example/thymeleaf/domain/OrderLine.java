package com.example.thymeleaf.domain;

import java.math.BigDecimal;

public record OrderLine(Product product, int amount, BigDecimal purchasePrice) {

    public OrderLine(Product product, int amount, String purchasePrice) {
        this(product, amount, new BigDecimal(purchasePrice));
    }
}
