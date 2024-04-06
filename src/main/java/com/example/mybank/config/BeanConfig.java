package com.example.mybank.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.example.mybank.common", "com.example.mybank.controller", "com.example.mybank.dao",
        "com.example.mybank.event", "com.example.mybank.service", "com.example.mybank.jms"})
public class BeanConfig {
}
