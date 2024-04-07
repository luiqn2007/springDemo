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

    private static final Random RANDOM = new Random(System.currentTimeMillis());
    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "dev, jpa, jms, activemq-broker");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.example.mybank.config");
        context.refresh();
        runSql(context);
    }

    private static void runSql(AnnotationConfigApplicationContext context) {
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
                .email("test_" + i + "@test.com")
                .build()));
        fixedDepositService.getAllFds(5000, 30).forEach(System.out::println);
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
        LOGGER.info(bankAccountService.getBankAccount(bankAccountDetails.getAccountId()));
        bankAccountService.getHighValueFds(10000).forEach(LOGGER::info);
    }
}
