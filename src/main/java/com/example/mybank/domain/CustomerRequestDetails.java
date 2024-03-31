package com.example.mybank.domain;

import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Named
public class CustomerRequestDetails {

    private String type, description, category, assignedDepartment, status, resolutionTimeInDays;
    private Date submissionDate;

    public CustomerRequestDetails() {
        System.out.println("Create ConsumerRequestDetails");
    }
}
