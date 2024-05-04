package com.example.mybank_xml.listener;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import lombok.Setter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Setter
public class EmailMessageListeners implements MessageListener {

    private transient SimpleMailMessage receivedMailMessage;
    private JavaMailSender mailSender;

    @Override
    public void onMessage(Message message) {
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
