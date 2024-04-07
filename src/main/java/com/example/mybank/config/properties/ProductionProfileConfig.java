package com.example.mybank.config.properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.IOException;
import java.util.Properties;

@Configuration
@Profile("production")
public class ProductionProfileConfig implements ProfileConfigExt {

    @Bean
    public Properties jdbcProperties() throws IOException {
        return loadProperties("mysqlDB");
    }

    @Bean
    public Properties mongoProperties() throws IOException {
        return loadProperties("mongodb");
    }

    @Bean
    public Properties activemqProperties() throws IOException {
        return loadProperties("activemq");
    }

    @Bean
    public Properties emailProperties() throws IOException {
        return loadProperties("email");
    }

    @Bean
    public Properties emailTemplatesProperties() throws IOException {
        return loadProperties("emailTemplates");
    }
}
