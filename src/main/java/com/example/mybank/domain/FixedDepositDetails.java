package com.example.mybank.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class FixedDepositDetails {

    private static Logger LOGGER = LogManager.getLogger();

    @Min(0)
    private long id;
    @Min(1000)
    private float depositAmount;
    private int tenure;
    @NotBlank
    private String email;

    public FixedDepositDetails() {
        LOGGER.info("Created instance of FixedDepositDetails");
    }
}
