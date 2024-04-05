package com.example.mybank.mongodb_domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class MongoFixedDepositDetails {
    @Id
    private ObjectId fixedDepositId;
    private int fdAmount;

    public MongoFixedDepositDetails() {
        fixedDepositId = ObjectId.get();
    }
}
