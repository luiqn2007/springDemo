package com.example.mybank.config;

import com.example.mybank.converter.MoneyLongFormatterFactory;
import com.example.mybank.converter.IdToFixedDepositDetailsConverter;
import com.example.mybank.interceptor.MyRequestHandlerInterceptor;
import com.example.mybank.service.FixedDepositService;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Setter
@Configuration
public class WebConfigurer implements WebMvcConfigurer, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");

        registry.addInterceptor(new MyRequestHandlerInterceptor());
        registry.addInterceptor(localeChangeInterceptor);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        IdToFixedDepositDetailsConverter fddConverter = new IdToFixedDepositDetailsConverter();
        fddConverter.setFixedDepositService(applicationContext.getBean(FixedDepositService.class));
        MoneyLongFormatterFactory moneyLongFormatterFactory = new MoneyLongFormatterFactory();

        registry.addConverter(fddConverter);
        registry.addFormatterForFieldAnnotation(moneyLongFormatterFactory);
    }


    @Bean
    public CookieLocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(Locale.ENGLISH);
        return resolver;
    }

    @Bean
    public StandardServletMultipartResolver standardServletMultipartResolver() {
        return new StandardServletMultipartResolver();
    }
}

