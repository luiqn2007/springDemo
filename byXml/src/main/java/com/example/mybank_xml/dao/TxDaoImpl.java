package com.example.mybank_xml.dao;

import com.example.mybank_xml.domain.Tx;

import java.util.ArrayList;
import java.util.List;

public class TxDaoImpl implements TxDao {

    @Override
    public List<Tx> getTransaction(int accountNumber) {
        List<Tx> txList = new ArrayList<>();
        txList.add(new Tx(1, "High value money transfer", "complete"));
        txList.add(new Tx(2, "High value money transfer", "in process"));
        return txList;
    }
}
