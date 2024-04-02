package com.example.mybank.dao;

import com.example.mybank.domain.Account;
import com.example.mybank.sql.AccountMappingSqlQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {

    private SimpleJdbcInsert simpleJdbcInsert;
    private MappingSqlQuery<Account> mappingSqlQuery;

    @Autowired
    private void setDataSource(DataSource dataSource) {
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("bank_account_details")
                .usingGeneratedKeyColumns("ACCOUNT_ID");
        mappingSqlQuery = new AccountMappingSqlQuery(dataSource);
    }

    @Override
    public Account getAccount(int id) {
        return mappingSqlQuery.findObject(id);
    }

    @Override
    public int createAccount(Account account) {
        Map<String, Object> params = new HashMap<>();
        params.put("BALANCE_AMOUNT", account.getBalanceAmount());
        params.put("LAST_TRANSACTION_TS", new Date(account.getLastTransactionDate().getTime()));
        int accountId = simpleJdbcInsert.executeAndReturnKey(params).intValue();
        account.setAccountId(accountId);
        return accountId;
    }
}
