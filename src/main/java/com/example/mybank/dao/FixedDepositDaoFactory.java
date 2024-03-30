package com.example.mybank.dao;

import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class FixedDepositDaoFactory {

    public FixedDepositDao getFixedDepositDao(String type) {
        return switch (type.toLowerCase(Locale.ROOT)) {
            case "jdbc" -> new FixedDepositJdbcDao();
            case "hibernate" -> new FixedDepositHibernateDao();
            case "ibatis" -> new FixedDepositIbatisDao();
            default -> new FixedDepositDefaultDao();
        };
    }
}
