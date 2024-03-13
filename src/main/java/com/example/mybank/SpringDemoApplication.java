package com.example.mybank;

import com.example.mybank.chapter01.FixedDepositControllerV1;
import com.example.mybank.chapter02.controller.FixedDepositControllerV2;
import com.example.mybank.controller.FixedDepositController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringDemoApplication {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        chapter03();
    }

    static void defaultApp(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }

    static void chapter01() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:cp01_applicationContext.xml");
        FixedDepositControllerV1 controller = context.getBean("controller", FixedDepositControllerV1.class);
        LOGGER.info("Submission status of fixed deposit: " + controller.submit());
        LOGGER.info("Returned fixed deposit info: " + controller.get());
    }

    static void chapter02() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:cp02_applicationContext.xml");
        FixedDepositControllerV2 controller = context.getBean("controller", FixedDepositControllerV2.class);
        LOGGER.info("Submission status of fixed deposit: " + controller.submit());
        LOGGER.info("Returned fixed deposit info: " + controller.get());
    }

    static void chapter03() {
        var context = new ClassPathXmlApplicationContext("classpath:cp03_applicationContext.xml");
    }
}
