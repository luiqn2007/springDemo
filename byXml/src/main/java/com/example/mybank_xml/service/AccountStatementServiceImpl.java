package com.example.mybank_xml.service;

import com.example.mybank_xml.dao.AccountStatementDao;
import com.example.mybank_xml.domain.AccountStatement;
import lombok.Setter;

import java.util.Date;

@Setter
public class AccountStatementServiceImpl implements AccountStatementService {

    private AccountStatementDao accountStatementDao;

    @Override
    public AccountStatement getAccountStatement(Date from, Date to) {
        return accountStatementDao.getAccountStatement(from, to);
    }
}
