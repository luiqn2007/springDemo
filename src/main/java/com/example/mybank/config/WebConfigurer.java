package com.example.mybank.config;

import com.example.mybank.converter.IdToFixedDepositDetailsConverter;
import com.example.mybank.converter.MoneyLongFormatterFactory;
import com.example.mybank.interceptor.MyRequestHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    private IdToFixedDepositDetailsConverter idToFixedDepositDetailsConverter;
    @Autowired
    private MoneyLongFormatterFactory moneyLongFormatterFactory;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(idToFixedDepositDetailsConverter);
        registry.addFormatterForFieldAnnotation(moneyLongFormatterFactory);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");

        registry.addInterceptor(new MyRequestHandlerInterceptor());
        registry.addInterceptor(localeChangeInterceptor);
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

