package com.example.mybank.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {

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
}
