package com.example.mybank.sql;

import com.example.mybank.domain.FixedDepositDetails;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class FixedDepositDetailsMappingSqlQuery extends MappingSqlQuery<FixedDepositDetails> {

    private static final String SQL_QUERY_DEPOSIT_BY_ID = "select * from fixed_deposit_details where FIXED_DEPOSIT_ID = ?";

    public FixedDepositDetailsMappingSqlQuery(DataSource ds) {
        super(ds, SQL_QUERY_DEPOSIT_BY_ID);
        setParameters(new SqlParameter("id", Types.INTEGER));
    }

    @Override
    protected FixedDepositDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        return FixedDepositDetails.builder()
                .id(rs.getInt("FIXED_DEPOSIT_ID"))
                .bankAccountId(rs.getInt("ACCOUNT_ID"))
                .creationDate(rs.getDate("FD_CREATION_DATE"))
                .depositAmount(rs.getInt("AMOUNT"))
                .tenure(rs.getInt("TENURE"))
                .active(rs.getString("ACTIVE").equals("Y"))
                .build();
    }
}
