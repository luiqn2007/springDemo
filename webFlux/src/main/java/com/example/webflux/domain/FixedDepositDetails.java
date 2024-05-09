package com.example.webflux.domain;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;

import java.util.Date;

@Getter
@Setter
@ToString
public class FixedDepositDetails {
    @Id
    private ObjectId fixedDepositId = ObjectId.get();

    private Date fdCreationDate;

    private int fdAmount;

    private int tenure;

    private String active;
}