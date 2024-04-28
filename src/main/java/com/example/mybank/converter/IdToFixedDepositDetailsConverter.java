package com.example.mybank.converter;

import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.service.FixedDepositService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Setter
public class IdToFixedDepositDetailsConverter implements Converter<Integer, FixedDepositDetails> {

    private FixedDepositService fixedDepositService;

    @Override
    public FixedDepositDetails convert(Integer id) {
        return fixedDepositService.getFixedDepositDetails(id);
    }
}
