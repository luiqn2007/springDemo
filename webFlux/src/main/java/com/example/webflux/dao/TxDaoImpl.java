package com.example.webflux.dao;

import com.example.webflux.domain.Tx;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

@Singleton
@Named("txDao")
public class TxDaoImpl implements TxDao {

    @Override
    public List<Tx> getTransaction(int accountNumber) {
        List<Tx> txList = new ArrayList<>();
        txList.add(new Tx(1, "High value money transfer", "complete"));
        txList.add(new Tx(2, "High value money transfer", "in process"));
        return txList;
    }
}
