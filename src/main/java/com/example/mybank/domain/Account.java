package com.example.mybank.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class Account {
    int accountId;
    int balanceAmount;
    Date lastTransactionDate;
}
