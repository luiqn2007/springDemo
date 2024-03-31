package com.example.mybank.config;

import com.example.mybank.domain.DataSource;
import jakarta.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:config/mysqlDB.properties")
@Profile("production")
public class ProdDBConfig {

    @Inject
    Environment environment;

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setUrl(environment.getProperty("url"));
        dataSource.setUsername(environment.getProperty("username"));
        dataSource.setPassword(environment.getProperty("password"));
        dataSource.setDriverClass(environment.getProperty("driverClassName"));
        return dataSource;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
