package com.example.mybank.service;

import com.example.mybank.utils.Constants;
import org.springframework.core.io.ClassPathResource;

import java.io.FileOutputStream;
import java.util.Properties;

public class EventSenderSelectorServiceImpl implements EventSenderSelectorService {

    public EventSenderSelectorServiceImpl(String configFile) throws Exception {
        ClassPathResource resource = new ClassPathResource(configFile);
        try (FileOutputStream os = new FileOutputStream(resource.getFile())) {
            Properties properties = new Properties();
            properties.setProperty(Constants.EVENT_SENDER_CLASS_PROPERTY, "com.example.mybank.event.DatabaseEventSender");
            properties.store(os, null);
        }
    }
}
