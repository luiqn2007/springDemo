package com.example.mybank.spel;

import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@EqualsAndHashCode
@Component("testEnvironment")
public class EnvironmentBean {

    @Value("This is a test env.")
    private String env;

    public String environment = "test";

    public String getCity() {
        return "A city";
    }

    public String[] splitName(String fullName) {
        return fullName.split(" ");
    }
}
