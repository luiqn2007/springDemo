package com.example.mybank_xml.converter;

import com.example.mybank_xml.domain.FixedDepositDetails;
import com.example.mybank_xml.service.FixedDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IdToFixedDepositDetailsConverter implements Converter<Integer, FixedDepositDetails> {

    @Autowired
    private FixedDepositService fixedDepositService;

    @Override
    public FixedDepositDetails convert(Integer id) {
        return fixedDepositService.getFixedDepositDetails(id);
    }
}
