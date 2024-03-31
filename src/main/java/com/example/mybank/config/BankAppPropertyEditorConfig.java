package com.example.mybank.config;

import com.example.mybank.beans.MyPropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BankAppPropertyEditorConfig {

    @Bean
    public CustomEditorConfigurer customEditorConfigurer(MyPropertyEditorRegistrar registrar) {
        CustomEditorConfigurer configurer = new CustomEditorConfigurer();
        configurer.setPropertyEditorRegistrars(new PropertyEditorRegistrar[]{registrar});
        return configurer;
    }
}
