package com.example.mybank;

import com.example.mybank.domain.BankAccountDetails;
import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.mongodb_domain.MongoBankAccountDetails;
import com.example.mybank.mongodb_domain.MongoFixedDepositDetails;
import com.example.mybank.service.BankAccountService;
import com.example.mybank.service.BankAccountServiceMongoDBImpl;
import com.example.mybank.service.FixedDepositService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;
import java.util.Random;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;
import java.util.stream.IntStream;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class BankApp {

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "dev, spring-data, mongodb");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.example.mybank.config");
        context.refresh();
        runNoSql(context);
    }

    private static void runSql(AnnotationConfigApplicationContext context) {
        BankAccountService bankAccountService = context.getBean(BankAccountService.class);
        FixedDepositService fixedDepositService = context.getBean(FixedDepositService.class);

        int balanceAmount = random.nextInt(100000, 500000);
        BankAccountDetails accountDetails = BankAccountDetails.builder()
                .balanceAmount(100000)
                .lastTransactionDate(new Date())
                .build();
        bankAccountService.createAccount(accountDetails);
        System.out.println(accountDetails);

        int fixedDepositId = fixedDepositService.createFixedDeposit(FixedDepositDetails.builder()
                .bankAccountId(accountDetails)
                .creationDate(new Date())
                .depositAmount(random.nextInt(1000, Math.min(50000, balanceAmount)))
                .tenure(random.nextInt(6, 60))
                .active(random.nextBoolean() ? "Y" : "N")
                .build());
        System.out.println(fixedDepositService.getFixedDepositDetails(fixedDepositId));

        System.out.println("getAllFds ---------------------------------------------------------------------------");
        fixedDepositService.createFixedDeposit(FixedDepositDetails.builder()
                .bankAccountId(accountDetails)
                .tenure(6)
                .depositAmount(1000)
                .active("Y")
                .creationDate(new Date())
                .build());
        fixedDepositService.createFixedDeposit(FixedDepositDetails.builder()
                .bankAccountId(accountDetails)
                .tenure(6)
                .depositAmount(1000)
                .active("Y")
                .creationDate(new Date())
                .build());
        fixedDepositService.createFixedDeposit(FixedDepositDetails.builder()
                .bankAccountId(accountDetails)
                .tenure(6)
                .depositAmount(1000)
                .active("Y")
                .creationDate(new Date())
                .build());
        fixedDepositService.createFixedDeposit(FixedDepositDetails.builder()
                .bankAccountId(accountDetails)
                .tenure(6)
                .depositAmount(1000)
                .active("Y")
                .creationDate(new Date())
                .build());
        fixedDepositService.getAllFds(1000, 6).forEach(System.out::println);
    }

    private static void runNoSql(AnnotationConfigApplicationContext context) {
        BankAccountServiceMongoDBImpl bankAccountService = context.getBean(BankAccountServiceMongoDBImpl.class);

        RandomGeneratorFactory<RandomGenerator> rf = RandomGeneratorFactory.of("L128X256MixRandom");
        RandomGenerator random = rf.create(System.currentTimeMillis());

        MongoBankAccountDetails bankAccountDetails = MongoBankAccountDetails.builder()
                .balance(1000)
                .fixedDeposits(IntStream.range(0, 5).mapToObj(__ -> MongoFixedDepositDetails.builder()
                        .active(random.nextBoolean() ? "Y" : "N")
                        .tenure(random.nextInt(6, 60))
                        .creationDate(new Date())
                        .fdAmount(random.nextFloat(1000, 50000))
                        .build()).toList())
                .lastTransactionTimestamp(new Date())
                .build();
        bankAccountService.createAccount(bankAccountDetails);
        System.out.println(bankAccountService.getBankAccount(bankAccountDetails.getAccountId()));
        bankAccountService.getHighValueFds(10000).forEach(System.out::println);
    }
}
