package com.example.mybank;

import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.service.FixedDepositService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class BankApp {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        context.registerShutdownHook();

        FixedDepositService fixedDepositService = context.getBean("fixedDepositService", FixedDepositService.class);
        fixedDepositService.createFixedDeposit(FixedDepositDetails.builder()
                .id(0)
                .depositAmount(1000)
                .tenure(1)
                .email("abc@qq.com")
                .build());

        context.getBean("eventSenderFactory");
        context.getBean("eventSenderFactory");
        System.out.println(context.getBean("datasource"));
        System.out.println(context.getBean("webServiceConfiguration"));
        System.out.println(context.getBean("datasource-override"));
        System.out.println(context.getBean("webServiceConfiguration-override"));
    }

    static void defaultApp(String[] args) {
        SpringApplication.run(BankApp.class, args);
    }
}
