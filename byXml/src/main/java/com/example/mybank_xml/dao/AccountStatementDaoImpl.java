package com.example.mybank_xml.dao;

import com.example.mybank_xml.domain.AccountStatement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

public class AccountStatementDaoImpl implements AccountStatementDao {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public AccountStatement getAccountStatement(Date from, Date to) {
        LOGGER.info("getAccountStatement: {} - {}", from, to);
        return new AccountStatement();
    }
}
