package com.example.mybank.listener;

import com.example.mybank.domain.FixedDepositDetails;
import jakarta.jms.JMSConnectionFactory;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.ObjectMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
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
}
