package com.example.mybank.chapter02.service;

import com.example.mybank.chapter02.FixedDepositDetails;
import com.example.mybank.chapter02.dao.FixedDepositDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FixedDepositServiceImpl implements FixedDepositService {

    private static final Logger LOGGER = LogManager.getLogger();

    private FixedDepositDao fixedDepositDao;

    public FixedDepositServiceImpl() {
        LOGGER.info("initializing");
    }

    @Override
    public void setFixedDepositDao(FixedDepositDao fixedDepositDao) {
        LOGGER.info("Setting fixedDepositDao property");
        this.fixedDepositDao = fixedDepositDao;
    }

    @Override
    public FixedDepositDao getFixedDepositDao() {
        return fixedDepositDao;
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
