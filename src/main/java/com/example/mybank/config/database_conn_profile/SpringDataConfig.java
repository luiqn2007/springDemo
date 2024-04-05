package com.example.mybank.config.database_conn_profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@Profile("spring-data")
@EnableAsync
public class SpringDataConfig {
}
