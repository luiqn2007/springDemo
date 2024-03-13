package com.example.mybank.chapter03.service;

import com.example.mybank.chapter03.EmailMessageSender;
import com.example.mybank.chapter03.beans.FixedDepositDetails;
import com.example.mybank.chapter03.JmsMessageSender;
import com.example.mybank.chapter03.WebServiceInvoker;
import com.example.mybank.chapter03.dao.FixedDepositDao;
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
