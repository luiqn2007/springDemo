package com.example.mybank.listener;

import jakarta.jms.JMSConnectionFactory;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@Profile("jms")
@JMSConnectionFactory("jmsListenerContainerFactory")
public class EmailMessageListeners {

    @Autowired
    private transient MailSender mallSender;

    @Autowired
    @Qualifier("requestReceivedTemplate")
    private transient SimpleMailMessage receivedMailMessage;

    @Autowired
    @Qualifier("processedReceivedTemplate")
    private transient SimpleMailMessage processedMailMessage;
    @Autowired
    private JavaMailSender mailSender;

    @JmsListener(id = "emailReceivedMessageListener", destination = "emailQueueDestination")
    public void onSendReceivedEmail(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            receivedMailMessage.setTo(textMessage.getText());
            mallSender.send(receivedMailMessage);
            System.out.println("Send email " + receivedMailMessage);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    @JmsListener(id = "emailProcessedMessageListener", destination = "emailQueueDestination")
    public void onSendProcessedEmail(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            String sendTo = textMessage.getText();
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom(processedMailMessage.getFrom());
            mimeMessageHelper.setTo(sendTo);
            mimeMessageHelper.setSubject(processedMailMessage.getSubject());
            mimeMessageHelper.setText(processedMailMessage.getText());
            mailSender.send(mimeMessage);
            System.out.println("Send email " + mimeMessage);
        } catch (JMSException | MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
