package com.example.mybank.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@Profile("email")
public class EmailConfig {

    @Bean
    public JavaMailSender mailSender(@Qualifier("emailProperties") Properties properties) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(properties.getProperty("host"));
        mailSender.setProtocol(properties.getProperty("protocol"));
        mailSender.setPort(Integer.parseInt(properties.getProperty("port")));
        mailSender.setUsername(properties.getProperty("email"));
        mailSender.setPassword(properties.getProperty("password"));

        Properties javaMailProperties = new Properties();
        javaMailProperties.setProperty("mail.smtp.auth", "true");
        javaMailProperties.setProperty("mail.smtp.starttls.enable", "true");
        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }

    @Bean
    public SimpleMailMessage requestReceivedTemplate(@Qualifier("emailTemplatesProperties") Properties properties) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(properties.getProperty("from"));
        message.setSubject(properties.getProperty("received.subject"));
        message.setText(properties.getProperty("received.text"));
        return message;
    }

    @Bean
    public SimpleMailMessage processedReceivedTemplate(@Qualifier("emailTemplatesProperties") Properties properties) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(properties.getProperty("from"));
        message.setSubject(properties.getProperty("processed.subject"));
        message.setText(properties.getProperty("processed.text"));
        return message;
    }
}
