package com.example.mybank;

import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.service.CustomerRequestService;
import com.example.mybank.service.FixedDepositService;
import jakarta.validation.ConstraintViolationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;

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

        fixedDepositService.createFixedDeposit(new FixedDepositDetails(1, 0, 12, "someemail@somedimain.com"));
        fixedDepositService.createFixedDeposit(new FixedDepositDetails(1, 1000, 12, "someemail@somedimain.com"));

        CustomerRequestService customerRequestService = context.getBean(CustomerRequestService.class);
        try {
            customerRequestService.submitRequest("request type", "description < 20", Calendar.getInstance());
        } catch (ConstraintViolationException e) {
            System.out.println(e);
        }
        try {
            Calendar futureDate = Calendar.getInstance();
            futureDate.add(Calendar.YEAR, 1);
            customerRequestService.submitRequest("request type", "request: description > 20", futureDate);
        } catch (ConstraintViolationException e) {
            System.out.println(e);
        }
        try {
            Calendar pastDate = Calendar.getInstance();
            pastDate.add(Calendar.YEAR, -1);
            customerRequestService.submitRequest("request type", "request: description > 20", pastDate);
        } catch (ConstraintViolationException e) {
            System.out.println(e);
        }
    }

    static void defaultApp(String[] args) {
        SpringApplication.run(BankApp.class, args);
    }
}
