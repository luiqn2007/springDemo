package com.example.mybank.config;

import com.example.mybank.domain.DataSource;
import com.example.mybank.domain.WebServiceConfiguration;
import jakarta.inject.Named;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.util.Properties;

@Configuration
@ImportResource("classpath:bankapp-properties.xml")
public class DomainConfig {

    @Bean
    public WebServiceConfiguration webServiceConfiguration(@Named("webServiceProperties") Properties properties) {
        WebServiceConfiguration configuration = new WebServiceConfiguration();
        configuration.setUrl(properties.getProperty("webservice.url"));
        return configuration;
    }

    @Bean
    public DataSource datasource(@Named("databaseProperties") Properties properties) {
        DataSource dataSource = new DataSource();
        dataSource.setUrl(properties.getProperty("database.url"));
        dataSource.setUsername(properties.getProperty("database.username"));
        dataSource.setPassword(properties.getProperty("database.password"));
        dataSource.setDriverClass(properties.getProperty("database.driverClass"));
        return dataSource;
    }
}
