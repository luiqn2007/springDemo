package com.example.mybank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class, scanBasePackages = "com.example.mybank.config")
public class BankApp {

    public static void main(String[] args) {
        SpringApplication.run(BankApp.class);
    }
}
