package com.example.mybank.service;

import com.example.mybank.dao.AccountStatementDao;
import com.example.mybank.domain.AccountStatement;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Date;

@Singleton
@Named("accountStatementService")
@Qualifier("service")
public class AccountStatementServiceImpl implements AccountStatementService {

    @Inject
    private AccountStatementDao accountStatementDao;

    @Override
    public AccountStatement getAccountStatement(Date from, Date to) {
        return accountStatementDao.getAccountStatement(from, to);
    }
}
