package com.example.mybank.bean;

import lombok.Setter;
import org.springframework.beans.factory.support.MethodReplacer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;

@Setter
public class MyMethodReplacer implements MethodReplacer, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        if ("getMyBean".equals(method.getName())) {
            return applicationContext.getBean((String) args[0]);
        }
        return null;
    }
}
