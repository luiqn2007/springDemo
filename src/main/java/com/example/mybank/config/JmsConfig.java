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
@Profile("jms")
public class JmsConfig {

    @Bean
    @Profile("activemq-broker")
    public XBeanBrokerService activeMQBroker() throws Exception {
        XBeanBrokerService broker = new XBeanBrokerService();
        TransportConnector connector = new TransportConnector();
        connector.setUri(URI.create("tcp://localhost:61617"));
        broker.setTransportConnectors(List.of(connector));
        return broker;
    }

    @Bean
    @DependsOn("activeMQBroker")
    public ActiveMQConnectionFactory jmsFactory(@Qualifier("configProperties") Properties properties) {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL(properties.getProperty("activemq.url"));
        factory.setTrustedPackages(Arrays.stream(properties.getProperty("activemq.trustedPackages").split(",")).toList());
        if (properties.containsKey("activemq.username")) {
            factory.setUserName(properties.getProperty("username"));
        }
        if (properties.containsKey("activemq.password")) {
            factory.setPassword(properties.getProperty("password"));
        }
        return factory;
    }

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
