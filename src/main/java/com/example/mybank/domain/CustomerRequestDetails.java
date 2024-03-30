package com.example.mybank.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

@Setter
@Getter
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CustomerRequestDetails {

    private String type, description, category, assignedDepartment, status, resolutionTimeInDays;
    private Date submissionDate;

    public CustomerRequestDetails() {
        System.out.println("Create ConsumerRequestDetails");
    }
}
