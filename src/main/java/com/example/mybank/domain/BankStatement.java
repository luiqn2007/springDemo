package com.example.mybank.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
public class BankStatement {

    private Date transactionDate;
    private double amount;
    private String transactionType;
    private String referenceNumber;

    public String toString() {
        return "Your mini statement: Amount = " + amount
                + " , Ref. number = " + referenceNumber
                + " , Transaction Type = " + transactionType
                + " , Transaction Date = " + transactionDate;
    }
}