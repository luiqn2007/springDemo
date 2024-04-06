package com.example.mybank.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:jms-config.xml")
public class JmsConfig {
}
