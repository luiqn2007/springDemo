package com.example.mybank.event;

import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;

import java.util.Properties;

@AllArgsConstructor
public class DatabaseEventSender implements EventSender {

    private Properties properties;

    @Override
    public void sendEvent(Event e) {

    }
}
