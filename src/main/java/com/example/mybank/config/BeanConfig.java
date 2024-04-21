package com.example.mybank.config;

import com.example.mybank.annotation.FundTransfer;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

@Configuration
@ComponentScan(basePackages = "com.example.mybank")
public class BeanConfig {

    @Bean
    public Properties testProperties() throws IOException {
        Properties properties = new Properties();
        ClassPathResource resource = new ClassPathResource("properties/test.properties");
        try (InputStream inputStream = resource.getInputStream()) {
            properties.load(inputStream);
        }
        return properties;
    }

    @Bean
    public static CustomAutowireConfigurer customAutowireConfigurer() {
        CustomAutowireConfigurer configurer = new CustomAutowireConfigurer();
        configurer.setCustomQualifierTypes(Set.of(FundTransfer.class));
        return configurer;
    }

    @Bean
    public static CustomEditorConfigurer customEditorConfigurer() {
        CustomEditorConfigurer configurer = new CustomEditorConfigurer();
        PropertyEditorRegistrar registrar = registry -> {
            CustomDateEditor dateEditor = new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"), false);
            registry.registerCustomEditor(Date.class, dateEditor);
        };
        configurer.setPropertyEditorRegistrars(new PropertyEditorRegistrar[]{registrar});
        return configurer;
    }
}
