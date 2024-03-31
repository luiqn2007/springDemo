package com.example.mybank.config;

import com.example.mybank.annotation.FundTransfer;
import com.example.mybank.common.MyApplicationContext;
import com.example.mybank.postprocessor.ApplicationConfigurer;
import com.example.mybank.postprocessor.DependencyResolutionBeanPostProcessor;
import com.example.mybank.postprocessor.InstanceValidationBeanPostProcessor;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import java.util.Set;

@Configuration
public class BankAppPostProcessorConfig {

    @Bean
    public ApplicationConfigurer applicationConfigurer() {
        ApplicationConfigurer configurer = new ApplicationConfigurer();
        configurer.setOrder(0);
        return configurer;
    }

    @Bean
    public DependencyResolutionBeanPostProcessor dependencyResolutionBeanPostProcessor(MyApplicationContext context) {
        DependencyResolutionBeanPostProcessor processor = new DependencyResolutionBeanPostProcessor();
        processor.setOrder(1);
        processor.setContext(context);
        return processor;
    }

    @Bean
    public InstanceValidationBeanPostProcessor instanceValidationBeanPostProcessor() {
        InstanceValidationBeanPostProcessor processor = new InstanceValidationBeanPostProcessor();
        processor.setOrder(2);
        return processor;
    }

    @Bean
    public CommonAnnotationBeanPostProcessor commonAnnotationBeanPostProcessor() {
        return new CommonAnnotationBeanPostProcessor();
    }

    @Bean
    public CustomAutowireConfigurer customAutowireConfigurer() {
        CustomAutowireConfigurer configurer = new CustomAutowireConfigurer();
        configurer.setCustomQualifierTypes(Set.of(FundTransfer.class));
        return configurer;
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
}
