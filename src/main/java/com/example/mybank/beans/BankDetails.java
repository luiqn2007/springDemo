package com.example.mybank.beans;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Currency;
import java.util.Date;
import java.util.Properties;

@Getter
@Setter
@ToString
public class BankDetails {

    private String bankName;
    private byte[] bankPrimaryBusiness;
    private char[] headOfficeAddress;
    private char privateBank;
    private Currency primaryCurrency;
    private Date dateOfInception;
    private Properties branchAddresses;
}
