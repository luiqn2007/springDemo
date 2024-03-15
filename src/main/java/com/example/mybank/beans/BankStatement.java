package com.example.mybank.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class BankStatement {

    private Date transactionDate;
    private double amount;
    private String transactionType;
    private String referenceNumber;
}