package com.example.mybank.config.profile;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@Profile("hibernate")
@EnableTransactionManagement
public class HibernateProfileConfig {

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder sessionFactory = new LocalSessionFactoryBuilder(dataSource);
        sessionFactory.scanPackages("com.example.mybank.domain");
        sessionFactory.setProperty("hibernate.show_sql", "true");
        sessionFactory.setProperty("hibernate.id.new_generator_mappings", "false");
        return sessionFactory.buildSessionFactory();
    }

    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}
