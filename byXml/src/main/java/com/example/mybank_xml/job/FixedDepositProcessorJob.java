package com.example.mybank_xml.job;

import com.example.mybank_xml.domain.FixedDepositDetails;
import com.example.mybank_xml.respository.FixedDepositRepository;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import lombok.Setter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Setter
public class FixedDepositProcessorJob {

    private JavaMailSender mailSender;
    private transient SimpleMailMessage processedMailMessage;
    private FixedDepositRepository fixedDepositRepository;

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
