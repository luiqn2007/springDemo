package com.example.mybank.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Set;

@Getter
@Component
public class Services {

    @Autowired
    @Qualifier("service")
    private Set<Object> services;
}
