package com.example.mybank.dao;

import com.example.mybank.domain.FixedDepositDetails;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import org.springframework.context.annotation.Profile;

@Singleton
@Named("fixedDepositDao")
@Profile("ibatis")
public class FixedDepositIbatisDao implements FixedDepositDao {

    private final Int2ObjectMap<FixedDepositDetails> fixedDeposits = new Int2ObjectArrayMap<>();

    @Override
    public FixedDepositDetails getFixedDeposit(int id) {
        return fixedDeposits.get(id);
    }

    @Override
    public int createFixedDetail(FixedDepositDetails fixedDepositDetails) {
        fixedDeposits.put(fixedDepositDetails.getId(), fixedDepositDetails);
        return 1;
    }
}
