package com.example.mybank.job;

import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.repository.FixedDepositRepository;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FixedDepositProcessorJob {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    @Qualifier("processedReceivedTemplate")
    private transient SimpleMailMessage processedMailMessage;

    @Autowired
    private FixedDepositRepository fixedDepositRepository;

    @Scheduled(fixedRate = 1000)
    public void onSendProcessedEmail() {
        for (FixedDepositDetails inactiveFd : fixedDepositRepository.getInactiveFds()) {
            System.out.println("Send email to " + inactiveFd.getEmail());
            mailSender.send(mimeMessage -> {
                mimeMessage.setFrom(processedMailMessage.getFrom());
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(inactiveFd.getEmail()));
                mimeMessage.setSubject(processedMailMessage.getSubject());
                mimeMessage.setText(processedMailMessage.getText());
            });
            fixedDepositRepository.setFixedDepositAsActive(inactiveFd);
        }
        System.out.flush();
    }
}
