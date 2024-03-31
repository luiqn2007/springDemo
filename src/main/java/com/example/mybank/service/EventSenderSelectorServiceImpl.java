package com.example.mybank.service;

import com.example.mybank.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.util.Properties;

@Component("eventSenderSelectorService")
public class EventSenderSelectorServiceImpl implements EventSenderSelectorService {

    @Autowired
    public EventSenderSelectorServiceImpl(@Value("#{T(com.example.mybank.utils.Constants).EVENT_SENDER_PROPERTY_FILE_PATH}") String configFile) throws Exception {
        ClassPathResource resource = new ClassPathResource(configFile);
        try (FileOutputStream os = new FileOutputStream(resource.getFile())) {
            Properties properties = new Properties();
            properties.setProperty(Constants.EVENT_SENDER_CLASS_PROPERTY, "com.example.mybank.event.DatabaseEventSender");
            properties.store(os, null);
        }
    }
}
