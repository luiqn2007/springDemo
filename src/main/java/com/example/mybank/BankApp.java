package com.example.mybank;

import com.example.mybank.domain.Account;
import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.service.AccountService;
import com.example.mybank.service.FixedDepositService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;
import java.util.Random;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class BankApp {

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "dev, mysql");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.example.mybank.config");
        context.refresh();

        AccountService accountService = context.getBean(AccountService.class);
        FixedDepositService fixedDepositService = context.getBean(FixedDepositService.class);

        int balanceAmount = random.nextInt(100000, 500000);
        int accountId = accountService.createAccount(Account.builder()
                .balanceAmount(100000)
                .lastTransactionDate(new Date())
                .build());
        System.out.println("AccountId=" + accountId);
        System.out.println(accountService.getAccount(accountId));

        int fixedDepositId = fixedDepositService.createFixedDeposit(FixedDepositDetails.builder()
                .bankAccountId(accountId)
                .creationDate(new Date())
                .depositAmount(random.nextInt(1000, Math.min(50000, balanceAmount)))
                .tenure(random.nextInt(6, 60))
                .active(random.nextBoolean())
                .build());
        System.out.println("FixedDepositId=" + fixedDepositId);
        System.out.println(fixedDepositService.getFixedDepositDetails(fixedDepositId));
    }
}
