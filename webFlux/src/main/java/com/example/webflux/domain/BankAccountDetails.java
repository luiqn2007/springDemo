package com.example.webflux.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "bank_account_details")
public class BankAccountDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
    int accountId;

    @Column(name = "BALANCE_AMOUNT")
    float balanceAmount;

    @Column(name = "LAST_TRANSACTION_TS")
    Date lastTransactionDate;

    public BankAccountDetails(int accountId) {
        this.accountId = accountId;
        this.balanceAmount = 0;
        this.lastTransactionDate = new Date();
    }
}
