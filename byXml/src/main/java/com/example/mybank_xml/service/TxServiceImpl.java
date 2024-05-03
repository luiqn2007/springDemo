package com.example.mybank_xml.service;

import jakarta.inject.Named;
import jakarta.inject.Singleton;
import org.springframework.beans.factory.annotation.Qualifier;

@Singleton
@Named("txService")
@Qualifier("service")
public class TxServiceImpl implements TxService {
}
