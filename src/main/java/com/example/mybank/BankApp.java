package com.example.mybank;

import com.example.mybank.domain.BankAccountDetails;
import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.mongodb_domain.MongoBankAccountDetails;
import com.example.mybank.mongodb_domain.MongoFixedDepositDetails;
import com.example.mybank.service.BankAccountService;
import com.example.mybank.service.BankAccountServiceMongoDBImpl;
import com.example.mybank.service.FixedDepositService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;
import java.util.stream.IntStream;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class, scanBasePackages = "com.example.mybank.config")
public class BankApp {

    private static final Random RANDOM = new Random(System.currentTimeMillis());
    private static final Logger LOGGER = LogManager.getLogger();

    private static Properties testProperties;

    public static void main(String[] args) {
        runWeb();
    }

    private static void runSql() {
        System.setProperty("spring.profiles.active", "dev, jpa, jms, activemq-broker, email");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.example.mybank.config");
        context.refresh();
        testProperties = context.getBean("testProperties", Properties.class);

        BankAccountService bankAccountService = context.getBean(BankAccountService.class);
        FixedDepositService fixedDepositService = context.getBean(FixedDepositService.class);

        int balanceAmount = RANDOM.nextInt(100000, 500000);
        BankAccountDetails accountDetails = BankAccountDetails.builder()
                .balanceAmount(100000)
                .lastTransactionDate(new Date())
                .build();
        bankAccountService.createAccount(accountDetails);
        LOGGER.info(accountDetails);
        LOGGER.info(bankAccountService.getBankAccount(accountDetails.getAccountId()));

        int fixedDepositId = fixedDepositService.createFixedDeposit(FixedDepositDetails.builder()
                .bankAccountId(accountDetails)
                .creationDate(new Date())
                .depositAmount(RANDOM.nextInt(1000, Math.min(50000, balanceAmount)))
                .tenure(RANDOM.nextInt(6, 60))
                .active(RANDOM.nextBoolean() ? "Y" : "N")
                .email("test@test.com")
                .build());
        LOGGER.info(fixedDepositService.getFixedDepositDetails(fixedDepositId));

        LOGGER.info("getAllFds ---------------------------------------------------------------------------");
        IntStream.range(0, 10).forEach(i -> fixedDepositService.createFixedDeposit(FixedDepositDetails.builder()
                .bankAccountId(accountDetails)
                .tenure(RANDOM.nextInt(6, 60))
                .depositAmount(RANDOM.nextInt(1000, 10000))
                .active("Y")
                .creationDate(new Date())
                .email(testProperties.getProperty("test_email_receiver"))
                .build()));
        fixedDepositService.getAllFds(5000, 30).forEach(System.out::println);
    }

    private static void runNoSql() {
        System.setProperty("spring.profiles.active", "dev, jpa, jms, activemq-broker, email");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.example.mybank.config");
        context.refresh();
        testProperties = context.getBean("testProperties", Properties.class);

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
        LOGGER.info(bankAccountService.getBankAccount(bankAccountDetails.getAccountId()));
        bankAccountService.getHighValueFds(10000).forEach(LOGGER::info);
    }

    private static void runWeb() {
        System.setProperty("spring.profiles.active", "dev, jpa, jms, activemq-broker, email");
        SpringApplication.run(BankApp.class);
    }
}
