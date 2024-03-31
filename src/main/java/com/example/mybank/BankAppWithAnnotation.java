package com.example.mybank;

import com.example.mybank.config.BankAppPropConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class BankAppWithAnnotation {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "dev, mysql");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.example.mybank.config");
        context.refresh();
        BankAppPropConfig configuration = context.getBean(BankAppPropConfig.class);
    }
}
