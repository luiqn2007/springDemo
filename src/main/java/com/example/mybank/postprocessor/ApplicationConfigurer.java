package com.example.mybank.postprocessor;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.core.Ordered;

@Setter
@Getter
public class ApplicationConfigurer implements BeanFactoryPostProcessor, Ordered {

    private static final Logger LOGGER = LogManager.getLogger();

    private int order;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
//            beanDefinition.setAutowireCandidate(false);
            if (beanDefinition.isSingleton() && hasPrototypeDependency(beanFactory, beanDefinition)) {
                LOGGER.error("Single-scoped " + beanDefinitionName + " is depends on a prototype-scoped bean.");
            }
        }
    }

    boolean hasPrototypeDependency(ConfigurableListableBeanFactory beanFactory, BeanDefinition beanDefinition) {
        boolean isPrototype = false;
        MutablePropertyValues mutablePropertyValues = beanDefinition.getPropertyValues();
        PropertyValue[] propertyValues = mutablePropertyValues.getPropertyValues();
        for (PropertyValue propertyValue : propertyValues) {
            Object value = propertyValue.getValue();
            if (value instanceof RuntimeBeanReference reference) {
                String beanName = reference.getBeanName();
                BeanDefinition dependencyDefinition = beanFactory.getBeanDefinition(beanName);
                if (dependencyDefinition.isPrototype()) {
                    isPrototype = true;
                    break;
                }
            }
        }
        return isPrototype;
    }
}
