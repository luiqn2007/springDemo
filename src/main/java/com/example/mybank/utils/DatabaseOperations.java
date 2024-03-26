package com.example.mybank.utils;

import com.example.mybank.domain.BankStatement;
import com.example.mybank.domain.FixedDepositDetails;
import it.unimi.dsi.fastutil.longs.Long2ObjectArrayMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;

import java.util.Date;

/**
 * 数据库操作类
 */
public class DatabaseOperations {

    private static final Long2ObjectMap<FixedDepositDetails> fixedDeposits = new Long2ObjectArrayMap<>();

    public void saveFd(FixedDepositDetails fdd) {
        fixedDeposits.put(fdd.getId(), fdd);
    }

    public FixedDepositDetails loadFd(long id) {
        return fixedDeposits.get(id);
    }

    public BankStatement getMiniStatement() {
        return BankStatement.builder()
                .amount(100)
                .referenceNumber("Ref. no. 1")
                .transactionDate(new Date())
                .transactionType("credit")
                .build();
    }
}
