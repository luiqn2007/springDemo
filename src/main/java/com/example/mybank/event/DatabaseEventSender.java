package com.example.mybank.event;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Properties;

@NoArgsConstructor
@AllArgsConstructor
public class DatabaseEventSender implements EventSender {

    private Properties properties;

    @Override
    public void sendEvent(Event e) {

    }
}
