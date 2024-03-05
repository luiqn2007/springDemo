package com.example.mybank.chapter01;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FixedDepositService {

    private static final Logger LOGGER = LogManager.getLogger();

    private FixedDepositDao fixedDepositDao;

    public FixedDepositService() {
        LOGGER.info("initializing");
    }

    public void setFixedDepositDao(FixedDepositDao fixedDepositDao) {
        LOGGER.info("Setting fixedDepositDao property");
        this.fixedDepositDao = fixedDepositDao;
    }

    public FixedDepositDetails getFixedDepositDetails(long id) {
        return fixedDepositDao.getFixedDeposit(id);
    }

    public boolean createFixedDeposit(FixedDepositDetails fdd) {
        return fixedDepositDao.createFixedDetail(fdd);
    }
}
