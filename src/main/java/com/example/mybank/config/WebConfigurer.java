package com.example.mybank.config;

import com.example.mybank.converter.IdToFixedDepositDetailsConverter;
import com.example.mybank.interceptor.MyRequestHandlerInterceptor;
import com.example.mybank.service.FixedDepositService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Setter
@Configuration
public class WebConfigurer implements WebMvcConfigurer, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyRequestHandlerInterceptor());
    }

    @Autowired
    public void formattingConversionService(FormattingConversionService conversionService) {
        IdToFixedDepositDetailsConverter fddConverter = new IdToFixedDepositDetailsConverter();
        fddConverter.setFixedDepositService(applicationContext.getBean(FixedDepositService.class));
        conversionService.addConverter(fddConverter);
    }
}

