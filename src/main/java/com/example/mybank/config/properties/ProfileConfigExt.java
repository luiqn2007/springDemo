package com.example.mybank.config.properties;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public interface ProfileConfigExt {

    default Properties loadProperties(String name) throws IOException {
        Properties props = new Properties();
        ClassPathResource resource = new ClassPathResource("properties/" + name + ".properties");
        try (InputStream inputStream = resource.getInputStream()) {
            props.load(inputStream);
        }
        return props;
    }
}
