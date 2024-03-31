package com.example.mybank.controller;

import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.service.FixedDepositService;
import jakarta.annotation.Resource;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Getter
@Singleton
@Named("fixedDepositController")
public class FixedDepositControllerImpl implements FixedDepositController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Resource(name = "fixedDepositService")
    private FixedDepositService fixedDepositService;

    public FixedDepositControllerImpl() {
        LOGGER.info("initializing");
    }

    public void setFixedDepositService(FixedDepositService fixedDepositService) {
        LOGGER.info("Setting fixedDepositService property");
        this.fixedDepositService = fixedDepositService;
    }

    @Override
    public FixedDepositDetails get() {
        return fixedDepositService.getFixedDepositDetails(1L);
    }

    @Override
    public boolean submit(FixedDepositDetails fixedDepositDetails) {
        return fixedDepositService.createFixedDeposit(fixedDepositDetails);
    }
}
