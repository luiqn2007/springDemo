package com.example.mybank.mongodb_domain;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Date;

@ToString
@Builder
@AllArgsConstructor
@Entity
public class MongoFixedDepositDetails {
    @Id
    private ObjectId fixedDepositId;
    private float fdAmount;
    private int tenure;
    private Date creationDate;
    private String active;

    public MongoFixedDepositDetails() {
        fixedDepositId = ObjectId.get();
    }
}
