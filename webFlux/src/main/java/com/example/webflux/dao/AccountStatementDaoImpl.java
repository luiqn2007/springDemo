package com.example.webflux.dao;

import com.example.webflux.domain.AccountStatement;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

@Singleton
@Named("accountStatementDao")
public class AccountStatementDaoImpl implements AccountStatementDao {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public AccountStatement getAccountStatement(Date from, Date to) {
        LOGGER.info("getAccountStatement: {} - {}", from, to);
        return new AccountStatement();
    }
}
