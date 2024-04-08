package com.example.mybank.sql;

import com.example.mybank.domain.BankAccountDetails;
import com.example.mybank.domain.FixedDepositDetails;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FixedDepositDetailsByAmountAndTenureMappingSqlQuery extends MappingSqlQuery<FixedDepositDetails> {

    public FixedDepositDetailsByAmountAndTenureMappingSqlQuery(DataSource ds) {
        super(ds, "select * from fixed_deposit_details where AMOUNT = ?1 and TENURE = ?2");
        setParameters(new SqlParameter("amount", java.sql.Types.INTEGER), new SqlParameter("tenure", java.sql.Types.INTEGER));
    }

    @Override
    protected FixedDepositDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        return FixedDepositDetails.builder()
                .id(rs.getInt("FIXED_DEPOSIT_ID"))
                .bankAccountId(BankAccountDetails.builder()
                        .accountId(rs.getInt("ACCOUNT_ID"))
                        .build())
                .creationDate(rs.getDate("FD_CREATION_DATE"))
                .depositAmount(rs.getInt("AMOUNT"))
                .tenure(rs.getInt("TENURE"))
                .active(rs.getString("ACTIVE"))
                .build();
    }
}