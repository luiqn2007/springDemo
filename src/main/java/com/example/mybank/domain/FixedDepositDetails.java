package com.example.mybank.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class FixedDepositDetails {

    private static Logger LOGGER = LogManager.getLogger();

    @PositiveOrZero
    private int id;
    @PositiveOrZero
    private int bankAccountId;
    private Date creationDate;
    @Min(1000)
    @Max(50000)
    private int depositAmount;
    @Min(6)
    private int tenure;
    boolean active;

    public FixedDepositDetails() {
        LOGGER.info("Created instance of FixedDepositDetails");
    }
}
