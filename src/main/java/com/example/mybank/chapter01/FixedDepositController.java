package com.example.mybank.chapter01;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FixedDepositController {

    private static final Logger LOGGER = LogManager.getLogger();

    private FixedDepositService fixedDepositService;

    public FixedDepositController() {
        LOGGER.info("initializing");
    }

    public void setFixedDepositService(FixedDepositService fixedDepositService) {
        LOGGER.info("Setting fixedDepositService property");
        this.fixedDepositService = fixedDepositService;
    }

    public boolean submit() {
        FixedDepositDetails fdd = new FixedDepositDetails(1, 10000, 365, "someemail@something.com");
        return fixedDepositService.createFixedDeposit(fdd);
    }

    public FixedDepositDetails get() {
        return fixedDepositService.getFixedDepositDetails(1L);
    }
}
