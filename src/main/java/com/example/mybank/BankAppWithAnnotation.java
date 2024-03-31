package com.example.mybank;

import com.example.mybank.config.BankAppPropConfig;
import com.example.mybank.domain.Account;
import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.service.CustomerRequestService;
import com.example.mybank.service.FixedDepositService;
import com.example.mybank.service.FundTransferProcessor;
import jakarta.validation.ConstraintViolationException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Calendar;

@SpringBootApplication
public class BankAppWithAnnotation {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "dev, mysql");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.example.mybank.config");
        context.refresh();

        FixedDepositService fixedDepositService = context.getBean("fixedDepositService", FixedDepositService.class);
        fixedDepositService.createFixedDeposit(new FixedDepositDetails(0, 100, 1, "abc@qq.com"));

        context.getBean("eventSenderFactory");
        context.getBean("eventSenderFactory");
        System.out.println(context.getBean("datasource"));
        System.out.println(context.getBean("webServiceConfiguration"));
        System.out.println(context.getBean("datasource-override"));
        System.out.println(context.getBean("webServiceConfiguration-override"));

        fixedDepositService.createFixedDeposit(new FixedDepositDetails(1, 0, 12, "someemail@somedimain.com"));
        fixedDepositService.createFixedDeposit(new FixedDepositDetails(1, 1000, 12, "someemail@somedimain.com"));

        Account a = new Account(), b = new Account();
        FundTransferProcessor fundTransferProcessor = context.getBean(FundTransferProcessor.class);
        fundTransferProcessor.getDiffBankImmediateFundTransferService().transferFunds(a, b);
        fundTransferProcessor.getSameBankImmediateFundTransferService().transferFunds(a, b);
        fundTransferProcessor.getDiffBankNormalFundTransferService().transferFunds(a, b);
        fundTransferProcessor.getSameBankNormalFundTransferService().transferFunds(a, b);

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

        System.out.println(context.getBean("dbProps"));
    }
}
