package com.example.mybank.config;

import com.example.mybank.annotation.FundTransfer;
import com.example.mybank.handler.MyErrorHandler;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

@Configuration
@ComponentScan(basePackages = "com.example.mybank")
@EnableJpaRepositories("com.example.mybank.repository")
@EnableScheduling
@EnableJms
public class BeanConfig {

    @Bean
    public Properties testProperties() throws IOException {
        Properties properties = new Properties();
        ClassPathResource resource = new ClassPathResource("properties/test.properties");
        try (InputStream inputStream = resource.getInputStream()) {
            properties.load(inputStream);
        }
        return properties;
    }

    @Bean
    public static CustomAutowireConfigurer customAutowireConfigurer() {
        CustomAutowireConfigurer configurer = new CustomAutowireConfigurer();
        configurer.setCustomQualifierTypes(Set.of(FundTransfer.class));
        return configurer;
    }

    @Bean
    public static CustomEditorConfigurer customEditorConfigurer() {
        CustomEditorConfigurer configurer = new CustomEditorConfigurer();
        PropertyEditorRegistrar registrar = registry -> {
            CustomDateEditor dateEditor = new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"), false);
            registry.registerCustomEditor(Date.class, dateEditor);
        };
        configurer.setPropertyEditorRegistrars(new PropertyEditorRegistrar[]{registrar});
        return configurer;
    }

    @Bean
    public SimpleMailMessage requestReceivedTemplate(@Qualifier("testProperties") Properties properties) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(properties.getProperty("email.from"));
        message.setSubject(properties.getProperty("email.received.subject"));
        message.setText(properties.getProperty("email.received.text"));
        return message;
    }

    @Bean
    public SimpleMailMessage processedReceivedTemplate(@Qualifier("testProperties") Properties properties) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(properties.getProperty("email.from"));
        message.setSubject(properties.getProperty("email.processed.subject"));
        message.setText(properties.getProperty("email.processed.text"));
        return message;
    }

    // jdbc
    @Bean
    public NamedParameterJdbcTemplate namedJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    // jms
    @Bean
    public ActiveMQQueue fixedDepositDestination() {
        return new ActiveMQQueue("aQueueDestination");
    }

    // jms
    @Bean
    public ActiveMQQueue emailMessageDestination() {
        return new ActiveMQQueue("emailQueueDestination");
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new MyErrorHandler());
        return restTemplate;
    }
}