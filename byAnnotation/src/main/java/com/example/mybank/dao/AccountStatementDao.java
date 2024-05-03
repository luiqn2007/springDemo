package com.example.mybank.dao;

import com.example.mybank.domain.AccountStatement;

import java.util.Date;

public interface AccountStatementDao {

    AccountStatement getAccountStatement(Date from, Date to);
}
