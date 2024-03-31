package com.example.mybank.common;

import jakarta.inject.Named;
import jakarta.inject.Singleton;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@Setter
@Named
@Singleton
public class MyApplicationContext implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
}
