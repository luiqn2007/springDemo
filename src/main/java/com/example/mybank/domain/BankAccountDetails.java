package com.example.mybank.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Entity(name = "BankAccountDetails")
@Table(name = "bank_account_details")
public class BankAccountDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
    int accountId;

    @Column(name = "BALANCE_AMOUNT")
    float balanceAmount;

    @Column(name = "LAST_TRANSACTION_TS")
    Date lastTransactionDate;
}
