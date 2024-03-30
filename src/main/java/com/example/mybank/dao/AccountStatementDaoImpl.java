package com.example.mybank.dao;

import com.example.mybank.domain.AccountStatement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component("accountStatementDao")
public class AccountStatementDaoImpl implements AccountStatementDao {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public AccountStatement getAccountStatement(Date from, Date to) {
        LOGGER.info("getAccountStatement: {} - {}", from, to);
        return new AccountStatement();
    }
}
