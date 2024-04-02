package com.example.mybank.service;

import com.example.mybank.utils.PropertyConstants;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;

import java.io.FileOutputStream;
import java.util.Properties;

@Singleton
@Named("eventSenderSelectorService")
@Qualifier("service")
public class EventSenderSelectorServiceImpl implements EventSenderSelectorService {

    @Inject
    public EventSenderSelectorServiceImpl(@Value("#{T(com.example.mybank.utils.PropertyConstants).EVENT_SENDER_PROPERTY_FILE_PATH}") String configFile) throws Exception {
        ClassPathResource resource = new ClassPathResource(configFile);
        try (FileOutputStream os = new FileOutputStream(resource.getFile())) {
            Properties properties = new Properties();
            properties.setProperty(PropertyConstants.EVENT_SENDER_CLASS_PROPERTY, "com.example.mybank.event.DatabaseEventSender");
            properties.store(os, null);
        }
    }
}
