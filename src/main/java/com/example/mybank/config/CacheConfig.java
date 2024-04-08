package com.example.mybank.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:cache.xml")
public class CacheConfig {
}
