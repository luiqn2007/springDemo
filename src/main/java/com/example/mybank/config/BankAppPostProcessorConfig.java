package com.example.mybank.config;

import com.example.mybank.annotation.FundTransfer;
import com.example.mybank.beans.MyPropertyEditorRegistrar;
import com.example.mybank.common.MyApplicationContext;
import com.example.mybank.postprocessor.ApplicationConfigurer;
import com.example.mybank.postprocessor.DependencyResolutionBeanPostProcessor;
import com.example.mybank.postprocessor.InstanceValidationBeanPostProcessor;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class BankAppPostProcessorConfig {

    @Bean
    public static ApplicationConfigurer applicationConfigurer() {
        ApplicationConfigurer configurer = new ApplicationConfigurer();
        configurer.setOrder(0);
        return configurer;
    }

    @Bean
    public static DependencyResolutionBeanPostProcessor dependencyResolutionBeanPostProcessor(MyApplicationContext context) {
        DependencyResolutionBeanPostProcessor processor = new DependencyResolutionBeanPostProcessor();
        processor.setOrder(1);
        processor.setContext(context);
        return processor;
    }

    @Bean
    public static InstanceValidationBeanPostProcessor instanceValidationBeanPostProcessor() {
        InstanceValidationBeanPostProcessor processor = new InstanceValidationBeanPostProcessor();
        processor.setOrder(2);
        return processor;
    }

    @Bean
    public static CustomAutowireConfigurer customAutowireConfigurer() {
        CustomAutowireConfigurer configurer = new CustomAutowireConfigurer();
        configurer.setCustomQualifierTypes(Set.of(FundTransfer.class));
        return configurer;
    }

    @Bean
    public static CustomEditorConfigurer customEditorConfigurer(MyPropertyEditorRegistrar registrar) {
        CustomEditorConfigurer configurer = new CustomEditorConfigurer();
        configurer.setPropertyEditorRegistrars(new PropertyEditorRegistrar[]{registrar});
        return configurer;
    }
}
