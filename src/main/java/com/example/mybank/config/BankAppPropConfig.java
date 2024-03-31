package com.example.mybank.config;

import com.example.mybank.event.EventSender;
import com.example.mybank.event.EventSenderFactoryBean;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"com.example.mybank"})
public class BankAppPropConfig {

    @Bean
    public EventSender eventSenderFactory() throws Exception {
        return new EventSenderFactoryBean().getObject();
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    // mysql dev: config/mysqlDevDB.properties
    // production: config/mysqlDB.properties
    @Bean
    public Properties dbProps() throws IOException {
        PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
        factoryBean.setLocations(new ClassPathResource("config/mysqlDevDB.properties"));
        return factoryBean.getObject();
    }
}
