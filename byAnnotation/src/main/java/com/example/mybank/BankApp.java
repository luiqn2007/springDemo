package com.example.mybank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.mybank.config")
public class BankApp {

    public static void main(String[] args) {
        SpringApplication.run(BankApp.class);
    }
}
