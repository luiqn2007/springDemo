package com.example.mybank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class WebConfig {
    
    @Bean
    public InternalResourceViewResolver viewResolver() {
        return new InternalResourceViewResolver("/jsp/", ".jsp");
    }
}
