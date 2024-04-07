package com.example.mybank.service;

import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.repository.FixedDepositRepository;
import jakarta.jms.ObjectMessage;
import jakarta.jms.TextMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("fixedDepositService")
@Profile("jpa")
public class FixedDepositServiceJpaDataImpl implements FixedDepositService {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private FixedDepositRepository fixedDepositRepository;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    @Transactional
    public FixedDepositDetails getFixedDepositDetails(int id) {
        return fixedDepositRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public int createFixedDeposit(FixedDepositDetails fixedDepositDetails) {

        jmsTemplate.send("emailQueueDestination", session -> {
            TextMessage message = session.createTextMessage();
            message.setText(fixedDepositDetails.getEmail());
            LOGGER.warn("Send {}", message);
            return message;
        });

        int id = fixedDepositRepository.save(fixedDepositDetails).getId();

        jmsTemplate.send("aQueueDestination", session -> {
            ObjectMessage message = session.createObjectMessage();
            message.setObject(fixedDepositDetails);
            LOGGER.warn("Send {}", message);
            return message;
        });

        return id;
    }

    @Override
    @Transactional
    public Iterable<FixedDepositDetails> getHighValueFds(int minValue) {
        return fixedDepositRepository.findAllByDepositAmountGreaterThanEqual(minValue);
    }

    @Override
    @Transactional
    public Iterable<FixedDepositDetails> getAllFds(int amount, int tenure) {
        return fixedDepositRepository.findAllByDepositAmountAndTenure(amount, tenure);
    }
}
