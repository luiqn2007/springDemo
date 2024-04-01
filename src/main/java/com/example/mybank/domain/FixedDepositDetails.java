package com.example.mybank.domain;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class FixedDepositDetails {

    private static Logger LOGGER = LogManager.getLogger();

    @PositiveOrZero
    private long id;
    @Min(1000)
    @Max(50000)
    private float depositAmount;
    @Min(6)
    private int tenure;
    @NotBlank
    @Size(min = 5, max = 100)
    private String email;

    public FixedDepositDetails() {
        LOGGER.info("Created instance of FixedDepositDetails");
    }
}
