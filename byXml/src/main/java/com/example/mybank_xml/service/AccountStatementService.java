package com.example.mybank_xml.service;

import com.example.mybank_xml.domain.AccountStatement;

import java.util.Date;

public interface AccountStatementService {

    AccountStatement getAccountStatement(Date from, Date to);
}
