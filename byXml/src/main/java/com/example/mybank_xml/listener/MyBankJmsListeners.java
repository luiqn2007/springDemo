package com.example.mybank_xml.listener;

import com.example.mybank_xml.domain.FixedDepositDetails;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyBankJmsListeners implements MessageListener {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        try {
            FixedDepositDetails fdDetails = (FixedDepositDetails) objectMessage.getObject();
            LOGGER.warn("Received Fixed Deposit Details : {}", fdDetails);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
