package com.example.mybank.config.properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.IOException;
import java.util.Properties;

@Configuration
@Profile("dev")
public class DevProfileConfig implements ProfileConfigExt {

    @Bean
    public Properties mysqlProperties() throws IOException {
        return loadProperties("mysqlDevDB");
    }

    @Bean
    public Properties mongoProperties() throws IOException {
        return loadProperties("mongodb");
    }

    @Bean
    public Properties activemqProperties() throws IOException {
        return loadProperties("activemq");
    }
}
