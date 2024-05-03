package com.example.mybank;

import jakarta.persistence.*;
import lombok.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"creationDate", "depositAmount", "tenure", "active", "email"})
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "fixed_deposit_details")
public class FixedDepositDetails implements Serializable {

    public static final long MIN_DEPOSIT_AMOUNT = 1000L;
    public static final long MAX_DEPOSIT_AMOUNT = 50000L;
    public static final int MIN_TENURE = 6;

    @Serial
    private static final long serialVersionUID = 1L;
    private static Logger LOGGER = LogManager.getLogger();

    @Id
    @Column(name = "FIXED_DEPOSIT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ACCOUNT_ID", nullable = false)
    private BankAccountDetails bankAccountId;
    @Column(name = "FD_CREATION_DATE")
    private Date creationDate;
    @Column(name = "FD_MATURITY_DATE")
    private Date maturityDate;
    @Column(name = "AMOUNT")
    private long depositAmount;
    @Column(name = "TENURE")
    private int tenure;
    @Column(name = "ACTIVE", insertable = false)
    private String active;
    @Column(name = "EMAIL")
    private String email;
}
