package com.example.mybank.listener;

import jakarta.jms.JMSConnectionFactory;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@JMSConnectionFactory("jmsListenerContainerFactory")
public class EmailMessageListeners {

    @Autowired
    @Qualifier("requestReceivedTemplate")
    private transient SimpleMailMessage receivedMailMessage;

    @Autowired
    private JavaMailSender mailSender;

    @JmsListener(id = "emailReceivedMessageListener", destination = "emailQueueDestination")
    public void onSendReceivedEmail(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            receivedMailMessage.setTo(textMessage.getText());
            mailSender.send(receivedMailMessage);
            System.out.println("Send email " + receivedMailMessage);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
