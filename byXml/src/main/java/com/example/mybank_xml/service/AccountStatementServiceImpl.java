package com.example.mybank_xml.service;

import com.example.mybank_xml.dao.AccountStatementDao;
import com.example.mybank_xml.domain.AccountStatement;
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
