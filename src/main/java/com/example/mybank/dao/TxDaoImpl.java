package com.example.mybank.dao;

import com.example.mybank.domain.Tx;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("txDao")
public class TxDaoImpl implements TxDao {

    @Override
    public List<Tx> getTransaction(int accountNumber) {
        List<Tx> txList = new ArrayList<>();
        txList.add(new Tx(1, "High value money transfer", "complete"));
        txList.add(new Tx(2, "High value money transfer", "in process"));
        return txList;
    }
}
