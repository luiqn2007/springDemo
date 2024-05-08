package com.example.webflux.dao;

import com.example.webflux.domain.AccountStatement;

import java.util.Date;

public interface AccountStatementDao {

    AccountStatement getAccountStatement(Date from, Date to);
}
