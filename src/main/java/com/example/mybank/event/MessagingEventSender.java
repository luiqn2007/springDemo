package com.example.mybank.event;

import lombok.AllArgsConstructor;

import java.util.Properties;

@AllArgsConstructor
public class MessagingEventSender implements EventSender {

    private Properties properties;

    @Override
    public void sendEvent(Event e) {
    }
}
