package com.example.mybank.dao;

import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.sql.FixedDepositDetailsMappingSqlQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Profile("mysql")
@Repository("fixedDepositDao")
public class FixedDepositMySQLDao implements FixedDepositDao {

    private SimpleJdbcInsert jdbcInsert;
    private MappingSqlQuery<FixedDepositDetails> mappingSqlQuery;

    @Autowired
    private void setDataSource(DataSource dataSource) {
        jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("fixed_deposit_details")
                .usingGeneratedKeyColumns("FIXED_DEPOSIT_ID");
        mappingSqlQuery = new FixedDepositDetailsMappingSqlQuery(dataSource);
    }

    @Override
    public FixedDepositDetails getFixedDeposit(int id) {
        return mappingSqlQuery.findObject(id);
    }

    @Override
    public int createFixedDetail(FixedDepositDetails fixedDepositDetails) {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("ACCOUNT_ID", fixedDepositDetails.getBankAccountId().getAccountId());
        arguments.put("FD_CREATION_DATE", new Date(fixedDepositDetails.getCreationDate().getTime()));
        arguments.put("AMOUNT", fixedDepositDetails.getDepositAmount());
        arguments.put("TENURE", fixedDepositDetails.getTenure());
        arguments.put("ACTIVE", fixedDepositDetails.getActive());
        return jdbcInsert.executeAndReturnKey(arguments).intValue();
    }
}
