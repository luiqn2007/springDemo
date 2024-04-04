package com.example.mybank.config.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

@Configuration
@Profile("dev")
public class DevProfileConfig {

    @Bean(name = "jdbc-properties")
    public Properties jdbcProperties() throws IOException {
        Properties properties = new Properties();
        ClassPathResource resource = new ClassPathResource("config/mysqlPCDevDB.properties");
        properties.load(resource.getInputStream());
        return properties;
    }
}
