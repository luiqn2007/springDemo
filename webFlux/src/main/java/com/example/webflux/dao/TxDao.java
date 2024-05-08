package com.example.webflux.dao;

import com.example.webflux.domain.Tx;

import java.util.List;

public interface TxDao {

    List<Tx> getTransaction(int accountNumber);
}
