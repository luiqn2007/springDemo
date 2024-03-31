package com.example.mybank.domain;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class Profile {

    private List<@Size(min = 5) String> friendNames;

    private Optional<@Size(min=11, max=11) String> phoneNumber;

    @PositiveOrZero
    private int income;

    @Positive
    private int age;

    public Profile(List<String> friendNames, Optional<String> phoneNumber, int income, int age) {
        this.friendNames = friendNames;
        this.phoneNumber = phoneNumber;
        this.income = income;
        this.age = age;
    }
}
