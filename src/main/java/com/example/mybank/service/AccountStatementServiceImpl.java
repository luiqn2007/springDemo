package com.example.mybank.service;

import com.example.mybank.dao.AccountStatementDao;
import com.example.mybank.domain.AccountStatement;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

@Setter
public class AccountStatementServiceImpl implements AccountStatementService {

    private static final Logger LOGGER = LogManager.getLogger();

    private AccountStatementDao accountStatementDao;

    @Override
    public AccountStatement getAccountStatement(Date from, Date to) {
        return accountStatementDao.getAccountStatement(from, to);
    }
}
