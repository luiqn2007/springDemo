package com.example.mybank.service;

import com.example.mybank.base.ServiceTemplate;
import com.example.mybank.dao.FixedDepositDao;
import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.event.EventSender;
import com.example.mybank.event.FixedDepositCreatedEvent;
import com.example.mybank.utils.Constants;
import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Getter
@Singleton
@Named("fixedDepositService")
@DependsOn("eventSenderSelectorService")
@Qualifier("service")
public class FixedDepositServiceImpl extends ServiceTemplate implements FixedDepositService {

    private static final Logger LOGGER = LogManager.getLogger();

    @Resource(name = "fixedDepositDao")
    private FixedDepositDao fixedDepositDao;
    private EventSender eventSender;

    @Inject
    private ValidatorFactory validatorFactory;

    @Inject
    public FixedDepositServiceImpl(@Value("#{T(com.example.mybank.utils.Constants).EVENT_SENDER_PROPERTY_FILE_PATH}") String appConfigFile) throws Exception {
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
    public FixedDepositDetails getFixedDepositDetails(int id) {
        return fixedDepositDao.getFixedDeposit(id);
    }

    @Override
    public boolean createFixedDeposit(FixedDepositDetails fdd) {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<FixedDepositDetails>> validations = validator.validate(fdd);
        if (!validations.isEmpty()) {
            LOGGER.error("Validation failed for FixedDepositDetails");
            return false;
        }

        LOGGER.info("Create fixed deposit");
        fixedDepositDao.createFixedDetail(fdd);

        FixedDepositCreatedEvent event = new FixedDepositCreatedEvent();
        event.setEventData(Map.of("amount", fdd.getDepositAmount()));
        eventSender.sendEvent(event);

        return true;
    }
}
