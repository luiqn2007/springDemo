package com.example.mybank.config.database_type_profile;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@Profile("!mongodb")
public class SqlProfileConfig {

    @Bean(destroyMethod = "")
    @Profile("dev")
    @Qualifier("dataSource")
    public DataSource dataSourceDev(@Qualifier("configProperties") Properties properties) throws SQLException {
        Properties properties1 = new Properties();
        properties1.setProperty("url", properties.getProperty("mysql.dev.url"));
        properties1.setProperty("username", properties.getProperty("mysql.dev.username"));
        properties1.setProperty("password", properties.getProperty("mysql.dev.password"));
        properties1.setProperty("driverClassName", properties.getProperty("mysql.dev.driverClassName"));
        return BasicDataSourceFactory.createDataSource(properties1);
    }

    @Bean(destroyMethod = "")
    @Profile("production")
    @Qualifier("dataSource")
    public DataSource dataSourceProduction(@Qualifier("configProperties") Properties properties) throws SQLException {
        Properties properties1 = new Properties();
        properties1.setProperty("url", properties.getProperty("mysql.production.url"));
        properties1.setProperty("username", properties.getProperty("mysql.production.username"));
        properties1.setProperty("password", properties.getProperty("mysql.production.password"));
        properties1.setProperty("driverClassName", properties.getProperty("mysql.production.driverClassName"));
        return BasicDataSourceFactory.createDataSource(properties1);
    }

    @Bean
    @Profile({"jdbc", "hibernate"})
    public TransactionTemplate transactionTemplate(@Qualifier("transactionManager") PlatformTransactionManager platformTransactionManager) {
        TransactionTemplate transactionTemplate = new TransactionTemplate();
        transactionTemplate.setTransactionManager(platformTransactionManager);
        transactionTemplate.setIsolationLevelName("ISOLATION_READ_COMMITTED");
        transactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRED");
        return transactionTemplate;
    }

    @Bean
    public NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
