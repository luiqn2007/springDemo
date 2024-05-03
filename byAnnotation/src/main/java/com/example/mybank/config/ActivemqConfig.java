package com.example.mybank.config;

import org.apache.activemq.broker.BrokerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Callable;

@Configuration
public class ActivemqConfig {

    private static final Logger LOGGER = LogManager.getLogger();

    @Bean
    public ActivemqApplication brokerService() throws Exception {
        ActivemqApplication application = new ActivemqApplication();
        application.call();
        return application;
    }

    public static class ActivemqApplication implements Callable<Void>, AutoCloseable {

        private BrokerService broker;

        @Override
        public Void call() throws Exception {
            LOGGER.info("Activemq starting...");
            broker = new BrokerService();
            broker.addConnector("tcp://localhost:61616");
            broker.start();
            return null;
        }

        @Override
        public void close() throws Exception {
            LOGGER.info("Activemq stopping...");
            if (broker != null) {
                broker.stop();
            }
        }

        public BrokerService getService() {
            return broker;
        }
    }
}
