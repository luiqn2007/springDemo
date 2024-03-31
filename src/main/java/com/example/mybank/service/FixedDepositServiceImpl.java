package com.example.mybank.service;

import com.example.mybank.base.ServiceTemplate;
import com.example.mybank.dao.FixedDepositDao;
import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.event.EventSender;
import com.example.mybank.event.FixedDepositCreatedEvent;
import com.example.mybank.utils.Constants;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

@Getter
@Component("fixedDepositService")
@DependsOn("eventSenderSelectorService")
public class FixedDepositServiceImpl extends ServiceTemplate implements FixedDepositService {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    @Qualifier("fixedDepositDao")
    private FixedDepositDao fixedDepositDao;
    private EventSender eventSender;

    @Autowired
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
    public FixedDepositDetails getFixedDepositDetails(long id) {
        return fixedDepositDao.getFixedDeposit(id);
    }

    @Override
    public boolean createFixedDeposit(FixedDepositDetails fdd) {
        fixedDepositDao.createFixedDetail(fdd);

        FixedDepositCreatedEvent event = new FixedDepositCreatedEvent();
        event.setEventData(Map.of("amount", fdd.getDepositAmount()));
        eventSender.sendEvent(event);

        return true;
    }

    @Override
    public void createFixedDeposit(long id, float depositAmount, int tenure, String email) {
        fixedDepositDao.createFixedDetail(id, depositAmount, tenure, email);
    }
}
