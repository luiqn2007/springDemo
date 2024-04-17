package com.example.mybank.config;

import jakarta.persistence.EntityManagerFactory;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("com.example.mybank.repository")
public class DatabaseConfig {

    @Bean(destroyMethod = "")
    public DataSource dataSource(@Qualifier("configProperties") Properties properties) throws SQLException {
        Properties properties1 = new Properties();
        properties1.setProperty("url", properties.getProperty("mysql.url"));
        properties1.setProperty("username", properties.getProperty("mysql.username"));
        properties1.setProperty("password", properties.getProperty("mysql.password"));
        properties1.setProperty("driverClassName", properties.getProperty("mysql.driverClassName"));
        return BasicDataSourceFactory.createDataSource(properties1);
    }

    // jdbc
    @Bean
    public NamedParameterJdbcTemplate namedJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    // jpa
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        // 配置 DataSource
        entityManagerFactory.setDataSource(dataSource);
        // 配置 Hibernate
        entityManagerFactory.setPackagesToScan("com.example.mybank.domain");
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Properties properties = new Properties();
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.id.new_generator_mappings", "false");
        entityManagerFactory.setJpaProperties(properties);
        return entityManagerFactory;
    }

    // jpa
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    // jpa
    @Bean
    public TransactionTemplate transactionTemplate(PlatformTransactionManager platformTransactionManager) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(platformTransactionManager);
        transactionTemplate.setIsolationLevelName("ISOLATION_READ_COMMITTED");
        transactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRED");
        return transactionTemplate;
    }
}
