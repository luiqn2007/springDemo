package com.example.mybank_xml.dao;

import com.example.mybank_xml.domain.Tx;

import java.util.List;

public interface TxDao {

    List<Tx> getTransaction(int accountNumber);
}
