package com.example.mybank.config;

import com.example.mybank.domain.DataSource;
import com.example.mybank.domain.WebServiceConfiguration;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class BankAppDomainConfig {

    @Bean
    public Properties bankBranchAddresses() throws IOException {
        PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
        factoryBean.setLocations(new ClassPathResource("config/database-addresses.properties"));
        return factoryBean.getObject();
    }

    @Bean
    public DataSource datasource() throws IOException {
        PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
        factoryBean.setLocations(new ClassPathResource("config/database.properties"));
        Properties datasourceProp = factoryBean.getObject();

        DataSource dataSource = new DataSource();
        dataSource.setUrl(datasourceProp.getProperty("database.url"));
        dataSource.setUsername(datasourceProp.getProperty("database.username"));
        dataSource.setPassword(datasourceProp.getProperty("database.password"));
        dataSource.setDriverClass(datasourceProp.getProperty("database.driverClass"));
        return dataSource;
    }

    @Bean
    public WebServiceConfiguration webServiceConfiguration() throws IOException {
        PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
        factoryBean.setLocations(new ClassPathResource("config/web-service.properties"));
        Properties datasourceProp = factoryBean.getObject();

        WebServiceConfiguration configuration = new WebServiceConfiguration();
        configuration.setUrl(datasourceProp.getProperty("webservice.url"));
        return configuration;
    }

    @Bean("datasource-override")
    public DataSource datasourceOverride() throws IOException {
        PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
        factoryBean.setLocations(new ClassPathResource("config/database-override.properties"));
        Properties datasourceProp = factoryBean.getObject();

        DataSource dataSource = new DataSource();
        dataSource.setUrl(datasourceProp.getProperty("datasource-override.url"));
        dataSource.setUsername(datasourceProp.getProperty("datasource-override.username"));
        dataSource.setPassword(datasourceProp.getProperty("datasource-override.password"));
        dataSource.setDriverClass(datasourceProp.getProperty("datasource-override.driverClass"));
        return dataSource;
    }

    @Bean("webServiceConfiguration-override")
    public WebServiceConfiguration webServiceConfigurationOverride() throws IOException {
        PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
        factoryBean.setLocations(new ClassPathResource("config/web-service-override.properties"));
        Properties datasourceProp = factoryBean.getObject();

        WebServiceConfiguration configuration = new WebServiceConfiguration();
        configuration.setUrl(datasourceProp.getProperty("webServiceConfiguration-override.url"));
        return configuration;
    }
}
