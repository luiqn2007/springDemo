package com.example.mybank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringDemoApplication {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    }

    static void defaultApp(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }
}
