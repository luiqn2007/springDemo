package com.example.mybank.postprocessor;

import com.example.mybank.common.DependencyResolver;
import com.example.mybank.common.MyApplicationContext;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

@Getter
@Setter
public class DependencyResolutionBeanPostProcessor implements BeanPostProcessor, Ordered {

    private static final Logger LOGGER = LogManager.getLogger();

    private int order;
    private MyApplicationContext context;

    public DependencyResolutionBeanPostProcessor() {
        LOGGER.info("Create DependencyResolutionBeanPostProcessor instance");
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (bean instanceof DependencyResolver resolver) {
            resolver.resolveDependency(context);
        }
        return bean;
    }
}
