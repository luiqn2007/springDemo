package com.example.mybank_xml.dao;

import com.example.mybank_xml.domain.AccountStatement;

import java.util.Date;

public interface AccountStatementDao {

    AccountStatement getAccountStatement(Date from, Date to);
}
