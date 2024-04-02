package com.example.mybank.sql;

import com.example.mybank.domain.Account;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class AccountMappingSqlQuery extends MappingSqlQuery<Account> {

    private static final String SQL_QUERY_DEPOSIT_BY_ID = "select * from bank_account_details where ACCOUNT_ID = ?";

    public AccountMappingSqlQuery(DataSource dataSource) {
        super(dataSource, SQL_QUERY_DEPOSIT_BY_ID);
        setParameters(new SqlParameter("id", Types.INTEGER));
    }

    @Override
    protected Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Account.builder()
                .accountId(rs.getInt("ACCOUNT_ID"))
                .balanceAmount(rs.getInt("BALANCE_AMOUNT"))
                .lastTransactionDate(rs.getDate("LAST_TRANSACTION_TS"))
                .build();
    }
}
