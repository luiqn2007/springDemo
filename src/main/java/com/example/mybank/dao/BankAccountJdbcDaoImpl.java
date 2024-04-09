package com.example.mybank.dao;

import com.example.mybank.domain.BankAccountDetails;
import com.example.mybank.sql.BankAccountDetailsByIdMappingSqlQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Profile("jdbc")
@Repository("bankAccountDao")
public class BankAccountJdbcDaoImpl implements BankAccountDao {

    private static final String SQL_SUBTRACT_AMOUNT =
            "UPDATE bank_account_details SET BALANCE_AMOUNT = BALANCE_AMOUNT - :amount WHERE ACCOUNT_ID = :accountId";
    private static final String SQL_GET_ACCOUNT_COUNT =
            "SELECT COUNT(*) FROM bank_account_details where ACCOUNT_ID = :accountId";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert jdbcInsert;
    private MappingSqlQuery<BankAccountDetails> mappingSqlQuery;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("bank_account_details")
                .usingGeneratedKeyColumns("ACCOUNT_ID");
        mappingSqlQuery = new BankAccountDetailsByIdMappingSqlQuery(dataSource);
    }

    @Override
    public void createBankAccount(BankAccountDetails bankAccountDetails) {
        Map<String, Object> params = new HashMap<>();
        params.put("BALANCE_AMOUNT", bankAccountDetails.getBalanceAmount());
        params.put("LAST_TRANSACTION_TS", new Date(bankAccountDetails.getLastTransactionDate().getTime()));
        int id = jdbcInsert.executeAndReturnKey(params).intValue();
        bankAccountDetails.setAccountId(id);
    }

    @Override
    public BankAccountDetails getBankAccount(int bankAccountId) {
        return mappingSqlQuery.findObject(bankAccountId);
    }

    @Override
    public void subtractAmount(int bankAccountId, float amount) {
        Map<String, Object> params = new HashMap<>();
        params.put("amount", amount);
        params.put("accountId", bankAccountId);
        jdbcTemplate.update(SQL_SUBTRACT_AMOUNT, params);
    }

    @Override
    public boolean isDuplicateAccount(int bankAccountId) {
        Map<String, Integer> params = Map.of("accountId", bankAccountId);
        return jdbcTemplate.queryForObject(SQL_GET_ACCOUNT_COUNT, params, Integer.class) > 0;
    }
}

