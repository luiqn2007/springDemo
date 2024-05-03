package com.example.mybank_xml.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@NoArgsConstructor
public class BankStatement {

    @Value("30-01-2012")
    private Date transactionDate;
    @Value("100000.00")
    private double amount;
    @Value("Credit")
    private String transactionType;
    @Value("1110202")
    private String referenceNumber;

    public String toString() {
        return "Your mini statement: Amount = " + amount
                + " , Ref. number = " + referenceNumber
                + " , Transaction Type = " + transactionType
                + " , Transaction Date = " + transactionDate;
    }
}