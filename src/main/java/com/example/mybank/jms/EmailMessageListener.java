package com.example.mybank.jms;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmailMessageListener implements MessageListener {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            String email = textMessage.getText();
            LOGGER.warn("Received Email: {}", email);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
