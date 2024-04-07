package com.example.mybank.listener;

import com.example.mybank.domain.FixedDepositDetails;
import jakarta.jms.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Profile("jms")
@JMSConnectionFactory("jmsListenerContainerFactory")
public class MyBankJmsListeners {

    private static final Logger LOGGER = LogManager.getLogger();

    @JmsListener(id = "fixedDepositsMessageListener", destination = "aQueueDestination")
    public void onReceiveFixedDepositDetails(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        try {
            FixedDepositDetails fdDetails = (FixedDepositDetails) objectMessage.getObject();
            LOGGER.warn("Received Fixed Deposit Details : {}", fdDetails);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    @JmsListener(id = "emailMessageLogListener", destination = "emailQueueDestination")
    public void onReceiveMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            String email = textMessage.getText();
            LOGGER.warn("Received Email: {}", email);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
