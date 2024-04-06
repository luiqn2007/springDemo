package com.example.mybank.config.database_config_profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@Profile("production")
public class ProductionProfileConfig {

    @Bean
    @Profile("!mongodb")
    public Properties jdbcProperties() throws IOException {
        Properties properties = new Properties();
        ClassPathResource resource = new ClassPathResource("properties/mysqlDB.properties");
        properties.load(resource.getInputStream());
        return properties;
    }

    @Bean
    @Profile("mongodb")
    public Properties mongoProperties() throws IOException {
        Properties props = new Properties();
        ClassPathResource resource = new ClassPathResource("properties/mongodb.properties");
        try (InputStream inputStream = resource.getInputStream()) {
            props.load(inputStream);
        }
        return props;
    }

}
