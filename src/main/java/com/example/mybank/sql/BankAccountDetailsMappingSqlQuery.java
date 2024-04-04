package com.example.mybank.sql;

import com.example.mybank.domain.BankAccountDetails;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class BankAccountDetailsMappingSqlQuery extends MappingSqlQuery<BankAccountDetails> {

    public BankAccountDetailsMappingSqlQuery(DataSource ds) {
        super(ds, "SELECT * FROM bank_account_details WHERE ACCOUNT_ID = ?");
        setParameters(new SqlParameter("id", Types.INTEGER));
    }

    @Override
    protected BankAccountDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        return null;
    }
}
