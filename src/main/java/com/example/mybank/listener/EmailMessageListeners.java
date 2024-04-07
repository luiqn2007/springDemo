package com.example.mybank.listener;

import jakarta.jms.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
@Profile("jms")
@JMSConnectionFactory("jmsListenerContainerFactory")
public class EmailMessageListeners {

    @Autowired
    private transient MailSender mallSender;

    @Autowired
    @Qualifier("requestReceivedTemplate")
    private transient SimpleMailMessage simpleMailMessage;

    @JmsListener(id = "emailMessageListener", destination = "emailQueueDestination")
    public void onSendEmail(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            simpleMailMessage.setTo(textMessage.getText());
            mallSender.send(simpleMailMessage);
            System.out.println("Send email " + simpleMailMessage);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
