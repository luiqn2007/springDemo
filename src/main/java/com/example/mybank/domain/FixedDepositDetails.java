package com.example.mybank.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "fixed_deposit_details")
public class FixedDepositDetails implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private static Logger LOGGER = LogManager.getLogger();

    @PositiveOrZero
    @Id
    @Column(name = "FIXED_DEPOSIT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ACCOUNT_ID", nullable = false)
    private BankAccountDetails bankAccountId;
    @Column(name = "FD_CREATION_DATE")
    private Date creationDate;
    @Min(1000)
    @Max(50000)
    @Column(name = "AMOUNT")
    private int depositAmount;
    @Min(6)
    @Column(name = "TENURE")
    private int tenure;
    @Column(name = "ACTIVE")
    private String active;
    @Column(name = "EMAIL")
    private String email;
}
