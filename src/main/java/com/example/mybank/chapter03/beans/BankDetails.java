package com.example.mybank.chapter03.beans;

import lombok.Data;

import java.util.Currency;
import java.util.Date;
import java.util.Properties;

@Data
public class BankDetails {

    private String bankName;
    private byte[] bankPrimaryBusiness;
    private char[] headOfficeAddress;
    private char privateBank;
    private Currency primaryCurrency;
    private Date dateOfInception;
    private Properties branchAddresses;
}
