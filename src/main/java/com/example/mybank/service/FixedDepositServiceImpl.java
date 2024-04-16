package com.example.mybank.service;

import com.example.mybank.Constants;
import com.example.mybank.dao.FixedDepositDao;
import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.event.EventSender;
import com.example.mybank.event.FixedDepositCreatedEvent;
import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@DependsOn("eventSenderSelectorService")
@Service("fixedDepositService")
@Profile({"jdbc", "hibernate"})
public class FixedDepositServiceImpl implements FixedDepositService {

    private static final Logger LOGGER = LogManager.getLogger();

    @Resource(name = "fixedDepositDao")
    private FixedDepositDao fixedDepositDao;
    private EventSender eventSender;

    @Inject
    private ValidatorFactory validatorFactory;

    @Inject
    public FixedDepositServiceImpl(@Value("#{T(com.example.mybank.Constants).EVENT_SENDER_PROPERTY_FILE_PATH}") String appConfigFile) throws Exception {
        ClassPathResource resource = new ClassPathResource(appConfigFile);
        if (resource.exists()) {
            try (InputStream is = resource.getInputStream()) {
                Properties properties = new Properties();
                properties.load(is);
                String eventSenderClassString = properties.getProperty(Constants.EVENT_SENDER_CLASS_PROPERTY);
                if (eventSenderClassString != null) {
                    Class<?> eventSenderClass = Class.forName(eventSenderClassString);
                    eventSender = (EventSender) eventSenderClass.getDeclaredConstructor().newInstance();
                }
            }
        }
    }

    @Override
    @Transactional
    public FixedDepositDetails getFixedDepositDetails(int id) {
        return fixedDepositDao.getFixedDeposit(id);
    }

    @Override
    @Transactional
    public int createFixedDeposit(FixedDepositDetails fixedDepositDetails) {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<FixedDepositDetails>> validations = validator.validate(fixedDepositDetails);
        if (!validations.isEmpty()) {
            LOGGER.error("Validation failed for FixedDepositDetails");
            return 0;
        }

        LOGGER.info("Create fixed deposit");
        int result = fixedDepositDao.createFixedDetail(fixedDepositDetails);

        FixedDepositCreatedEvent event = new FixedDepositCreatedEvent();
        event.setEventData(Map.of("amount", fixedDepositDetails.getDepositAmount()));
        eventSender.sendEvent(event);

        return result;
    }

    @Override
    @Transactional
    public Iterable<FixedDepositDetails> getHighValueFds(int minValue) {
        return fixedDepositDao.getHighValueFds(minValue);
    }

    @Override
    @Transactional
    public Iterable<FixedDepositDetails> getAllFds(int amount, int tenure) {
        return fixedDepositDao.getAllFds(amount, tenure);
    }

    @Override
    public List<FixedDepositDetails> findFixedDepositsByBankAccount(int bankAccountId) {
        return fixedDepositDao.findFixedDepositsByBankAccount(bankAccountId);
    }

    @Override
    public List<FixedDepositDetails> getFixedDeposits() {
        return fixedDepositDao.getFixedDeposits();
    }
}
