package com.example.mybank.config;

import com.example.mybank.webapp.HelloWebConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public HelloWebConfigurer helloWebConfigurer() {
        return new HelloWebConfigurer();
    }
}
