package com.example.mybank.service;

import com.example.mybank.domain.AccountStatement;

import java.util.Date;

public interface AccountStatementService {

    AccountStatement getAccountStatement(Date from, Date to);
}
