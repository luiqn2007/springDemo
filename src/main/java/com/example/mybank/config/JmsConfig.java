package com.example.mybank.config;

import org.apache.activemq.broker.TransportConnector;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.apache.activemq.xbean.XBeanBrokerService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Configuration
@EnableJms
public class JmsConfig {

    @Bean
    public CachingConnectionFactory cachingConnectionFactory(ActiveMQConnectionFactory connectionFactory) {
        return new CachingConnectionFactory(connectionFactory);
    }

    @Bean
    @Primary
    public JmsTransactionManager jmsTxManager(CachingConnectionFactory connectionFactory) {
        return new JmsTransactionManager(connectionFactory);
    }

    @Bean
    public ActiveMQQueue fixedDepositDestination() {
        return new ActiveMQQueue("aQueueDestination");
    }

    @Bean
    public ActiveMQQueue emailMessageDestination() {
        return new ActiveMQQueue("emailQueueDestination");
    }

    @Bean
    public JmsTemplate jmsTemplate(ActiveMQConnectionFactory connectionFactory, @Qualifier("fixedDepositDestination") ActiveMQQueue defaultQueue) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setDefaultDestination(defaultQueue);
        return jmsTemplate;
    }

    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerFactory(CachingConnectionFactory connectionFactory, JmsTransactionManager jmsTxManager) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setTransactionManager(jmsTxManager);
        return factory;
    }
}
