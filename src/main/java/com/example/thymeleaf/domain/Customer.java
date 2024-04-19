package com.example.thymeleaf.domain;

import java.util.Calendar;

public record Customer(int id, String name, Calendar customerSince) {
}
