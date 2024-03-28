package com.example.mybank.service;

import com.example.mybank.base.EmailMessageSender;
import com.example.mybank.base.JmsMessageSender;
import com.example.mybank.base.WebServiceInvoker;
import com.example.mybank.base.ServiceTemplate;
import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.dao.FixedDepositDao;
import com.example.mybank.event.EventSender;
import com.example.mybank.event.FixedDepositCreatedEvent;
import com.example.mybank.utils.Constants;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

import java.beans.ConstructorProperties;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

@Getter
public class FixedDepositServiceImpl extends ServiceTemplate implements FixedDepositService {

    private static final Logger LOGGER = LogManager.getLogger();

    @Setter
    private FixedDepositDao fixedDepositDao;
    private EventSender eventSender;

    @ConstructorProperties({"jmsMessageSender", "emailMessageSender", "webServiceInvoker", "appConfigFile"})
    public FixedDepositServiceImpl(JmsMessageSender jmsMessageSender, EmailMessageSender emailMessageSender, WebServiceInvoker webServiceInvoker,
                                   String appConfigFile) throws Exception {
        super(jmsMessageSender, emailMessageSender, webServiceInvoker);

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
}
