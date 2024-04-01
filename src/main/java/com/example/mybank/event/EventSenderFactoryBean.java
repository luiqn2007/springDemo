package com.example.mybank.event;

import jakarta.inject.Named;
import jakarta.inject.Singleton;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.function.Function;

@Setter
@Named("eventSenderFactory")
@Singleton
public class EventSenderFactoryBean implements FactoryBean<EventSender>, InitializingBean {

    public static final Logger LOGGER = LogManager.getLogger();

    private String databasePropertiesFile;
    private String webServicePropertiesFile;
    private String messagingPropertiesFile;

    @Override
    public EventSender getObject() throws Exception {
        LOGGER.info("Create EventSenderFactoryBean");
        EventSender eventSender;
        eventSender = createEventSenderFromProperties(databasePropertiesFile, DatabaseEventSender::new);
        if (eventSender != null) return eventSender;
        eventSender = createEventSenderFromProperties(webServicePropertiesFile, WebServiceEventSender::new);
        if (eventSender != null) return eventSender;
        eventSender = createEventSenderFromProperties(messagingPropertiesFile, MessagingEventSender::new);
        return eventSender;
    }

    private EventSender createEventSenderFromProperties(String propertiesFile, Function<Properties, EventSender> factory) {
        if (propertiesFile == null) return null;
        ClassPathResource resource = new ClassPathResource(propertiesFile);
        Properties properties = new Properties();
        if (!resource.exists()) return null;

        try (InputStream inputStream = resource.getInputStream()) {
            properties.load(inputStream);
            return factory.apply(properties);
        } catch (IOException e) {
            LOGGER.error("Error while loading properties file " + propertiesFile, e);
            return null;
        }
    }

    @Override
    public Class<?> getObjectType() {
        return EventSender.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.info("afterPropertiesSet");
    }
}
