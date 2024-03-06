package com.example.mybank.chapter03.controller;

import com.example.mybank.chapter03.FixedDepositDetails;
import com.example.mybank.chapter03.service.FixedDepositService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FixedDepositControllerImpl implements FixedDepositController {

    private static final Logger LOGGER = LogManager.getLogger();

    private FixedDepositService fixedDepositService;

    public FixedDepositControllerImpl() {
        LOGGER.info("initializing");
    }

    @Override
    public void setFixedDepositService(FixedDepositService fixedDepositService) {
        LOGGER.info("Setting fixedDepositService property");
        this.fixedDepositService = fixedDepositService;
    }

    @Override
    public FixedDepositService getFixedDepositService() {
        return fixedDepositService;
    }

    @Override
    public boolean submit() {
        FixedDepositDetails fdd = new FixedDepositDetails(1, 10000, 365, "someemail@something.com");
        return fixedDepositService.createFixedDeposit(fdd);
    }

    @Override
    public FixedDepositDetails get() {
        return fixedDepositService.getFixedDepositDetails(1L);
    }
}
