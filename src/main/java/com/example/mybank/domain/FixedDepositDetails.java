package com.example.mybank.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Getter
@Setter
@ToString
public class FixedDepositDetails {

    private static Logger LOGGER = LogManager.getLogger();

    private long id;
    private float depositAmount;
    private int tenure;
    private String email;

    public FixedDepositDetails() {
        LOGGER.info("Created instance of FixedDepositDetails");
    }
}
