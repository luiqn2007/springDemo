package com.example.mybank.controller;

import com.example.mybank.beans.FixedDepositDetails;
import com.example.mybank.service.FixedDepositService;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Setter
@Getter
public class FixedDepositControllerImpl implements FixedDepositController {

    private static final Logger LOGGER = LogManager.getLogger();

    private FixedDepositService fixedDepositService;

    public FixedDepositControllerImpl() {
        LOGGER.info("initializing");
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
