package com.example.mybank.domain;

import jakarta.inject.Named;
import jakarta.inject.Singleton;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

@Setter
@ToString
@Singleton
@Named
public class DataSource {

    @Value("#{dbProps.url}")
    private String url;

    @Value("#{dbProps.driverClassName}")
    private String driverClass;

    @Value("#{dbProps.username}")
    private String username;

    @Value("#{dbProps.password}")
    private String password;
}
