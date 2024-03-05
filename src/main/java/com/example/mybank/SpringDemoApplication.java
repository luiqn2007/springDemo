package com.example.mybank;

import com.example.mybank.chapter01.FixedDepositController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringDemoApplication {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
//        SpringApplication.run(SpringDemoApplication.class, args);
        chapter01();
    }

    static void chapter01() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:cp01_applicationContext.xml");
        FixedDepositController controller = context.getBean("controller", FixedDepositController.class);
        LOGGER.info("Submission status of fixed deposit: " + controller.submit());
        LOGGER.info("Returned fixed deposit info: " + controller.get());
    }
}
