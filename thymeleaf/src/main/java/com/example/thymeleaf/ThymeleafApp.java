package com.example.thymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "com.example.thymeleaf")
public class ThymeleafApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ThymeleafApp.class, args);
        System.out.println("Running...");
    }
}
