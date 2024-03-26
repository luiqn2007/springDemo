package com.example.mybank.service;

import com.example.mybank.base.EmailMessageSender;
import com.example.mybank.base.JmsMessageSender;
import com.example.mybank.base.WebServiceInvoker;
import com.example.mybank.base.ServiceTemplate;
import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.dao.FixedDepositDao;
import com.example.mybank.event.EventSender;
import com.example.mybank.event.FixedDepositCreatedEvent;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.ConstructorProperties;
import java.util.Map;

@Getter
@Setter
public class FixedDepositServiceImpl extends ServiceTemplate implements FixedDepositService {

    private static final Logger LOGGER = LogManager.getLogger();

    private FixedDepositDao fixedDepositDao;
    private EventSender eventSender;

    @ConstructorProperties({"jmsMessageSender", "emailMessageSender", "webServiceInvoker"})
    public FixedDepositServiceImpl(JmsMessageSender jmsMessageSender, EmailMessageSender emailMessageSender, WebServiceInvoker webServiceInvoker) {
        super(jmsMessageSender, emailMessageSender, webServiceInvoker);
        LOGGER.info("initializing");
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
