package com.example.mybank.postprocessor;

import com.example.mybank.common.InstanceValidator;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

@Setter
@Getter
public class InstanceValidationBeanPostProcessor implements BeanPostProcessor, Ordered {

    private static final Logger LOGGER = LogManager.getLogger();

    private int order;

    public InstanceValidationBeanPostProcessor() {
        LOGGER.info("Create InstanceValidationBeanPostProcessor");
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) {
        LOGGER.info("PostProcessing bean {}", beanName);
        if (bean instanceof InstanceValidator validator) {
            LOGGER.info("Validating instance of {}", beanName);
            validator.validateInstance();
        }
        return bean;
    }
}
