package com.example.thymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.thymeleaf")
public class ThymeleafApp {

    public static void main(String[] args) {
        SpringApplication.run(ThymeleafApp.class, args);
    }
}
