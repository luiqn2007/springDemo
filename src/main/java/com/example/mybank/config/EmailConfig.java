package com.example.mybank.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@Profile("email")
public class EmailConfig {

    @Bean
    public JavaMailSender mailSender(@Qualifier("configProperties") Properties properties) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(properties.getProperty("email.host"));
        mailSender.setProtocol(properties.getProperty("email.protocol"));
        mailSender.setPort(Integer.parseInt(properties.getProperty("email.port")));
        mailSender.setUsername(properties.getProperty("email.username"));
        mailSender.setPassword(properties.getProperty("email.password"));

        Properties javaMailProperties = new Properties();
        javaMailProperties.setProperty("mail.smtp.auth", "true");
        javaMailProperties.setProperty("mail.smtp.starttls.enable", "true");
        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }

    @Bean
    public SimpleMailMessage requestReceivedTemplate(@Qualifier("configProperties") Properties properties) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(properties.getProperty("email.from"));
        message.setSubject(properties.getProperty("email.received.subject"));
        message.setText(properties.getProperty("email.received.text"));
        return message;
    }

    @Bean
    public SimpleMailMessage processedReceivedTemplate(@Qualifier("configProperties") Properties properties) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(properties.getProperty("email.from"));
        message.setSubject(properties.getProperty("email.processed.subject"));
        message.setText(properties.getProperty("email.processed.text"));
        return message;
    }
}
