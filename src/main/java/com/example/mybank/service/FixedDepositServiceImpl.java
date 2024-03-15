package com.example.mybank.service;

import com.example.mybank.EmailMessageSender;
import com.example.mybank.JmsMessageSender;
import com.example.mybank.WebServiceInvoker;
import com.example.mybank.beans.FixedDepositDetails;
import com.example.mybank.dao.FixedDepositDao;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.ConstructorProperties;

@Getter
public class FixedDepositServiceImpl extends ServiceTemplate implements FixedDepositService {

    private static final Logger LOGGER = LogManager.getLogger();

    private FixedDepositDao fixedDepositDao;

    @ConstructorProperties({"jmsMessageSender", "emailMessageSender", "webServiceInvoker"})
    public FixedDepositServiceImpl(JmsMessageSender jmsMessageSender, EmailMessageSender emailMessageSender, WebServiceInvoker webServiceInvoker) {
        super(jmsMessageSender, emailMessageSender, webServiceInvoker);
        LOGGER.info("initializing");
    }

    @Override
    public void setFixedDepositDao(FixedDepositDao fixedDepositDao) {
        LOGGER.info("Setting fixedDepositDao property");
        this.fixedDepositDao = fixedDepositDao;
    }

    @Override
    public FixedDepositDetails getFixedDepositDetails(long id) {
        return fixedDepositDao.getFixedDeposit(id);
    }

    @Override
    public boolean createFixedDeposit(FixedDepositDetails fdd) {
        return fixedDepositDao.createFixedDetail(fdd);
    }
}
