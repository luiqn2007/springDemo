package com.example.mybank.config.database_config_profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@Profile("dev")
public class DevProfileConfig {

    @Bean
    @Profile("!mongodb")
    public Properties mysqlProperties() throws IOException {
        Properties properties = new Properties();
        ClassPathResource resource = new ClassPathResource("properties/mysqlPCDevDB.properties");
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
