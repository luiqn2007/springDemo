package com.example.mybank.config;

import com.example.mybank.domain.DataSource;
import jakarta.inject.Inject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:config/mysqlDB.properties")
@Profile("production")
public class ProdDBConfig {

    @Inject
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setUrl(environment.getProperty("database.url"));
        dataSource.setDriverClass(environment.getProperty("database.driverClass"));
        dataSource.setUsername(environment.getProperty("database.username"));
        dataSource.setPassword(environment.getProperty("database.password"));
        return dataSource;
    }
}
