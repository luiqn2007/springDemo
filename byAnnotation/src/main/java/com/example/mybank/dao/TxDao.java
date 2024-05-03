package com.example.mybank.dao;

import com.example.mybank.domain.Tx;

import java.util.List;

public interface TxDao {

    List<Tx> getTransaction(int accountNumber);
}
