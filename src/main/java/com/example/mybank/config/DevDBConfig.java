package com.example.mybank.config;

import com.example.mybank.domain.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:config/mysqlDevDB.properties")
@Profile("dev")
public class DevDBConfig {

    @Value("${url}")
    private String url;

    @Value("${driverClassName}")
    private String driverClass;

    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClass(driverClass);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
