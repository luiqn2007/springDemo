package com.example.mybank.domain;

import jakarta.inject.Named;
import jakarta.inject.Singleton;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;

import java.util.Currency;
import java.util.Date;
import java.util.Properties;

@Getter
@Singleton
@Named
@DependsOn("bankBranchAddresses")
public class BankDetails {

    @Value("My Person Bank")
    private String bankName;
    @Value("Retail banking")
    private byte[] bankPrimaryBusiness;
    @Value("Retail bank office")
    private char[] headOfficeAddress;
    @Value("y")
    private char privateBank;
    @Value("INR")
    private Currency primaryCurrency;
    @Value("30-01-2012")
    private Date dateOfInception;
    @Value("#{bankBranchAddresses}")
    private Properties branchAddresses;
}
