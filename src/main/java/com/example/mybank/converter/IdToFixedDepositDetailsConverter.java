package com.example.mybank.converter;

import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.service.FixedDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class IdToFixedDepositDetailsConverter implements Converter<Integer, FixedDepositDetails> {

    @Autowired
    private FixedDepositService fixedDepositService;

    @Override
    public FixedDepositDetails convert(Integer id) {
        return fixedDepositService.getFixedDepositDetails(id);
    }
}
