package com.example.mybank.config.database_type_profile;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Properties;

@Configuration
@Profile("mongodb")
@EnableMongoRepositories(basePackages = "com.example.mybank.mongodb_repository")
@EntityScan("com.example.mybank.mongodb_domain")
public class MongoDBProfileConfig {

    @Bean
    public MongoClient mongoClient(@Qualifier("configProperties") Properties properties) {
        return MongoClients.create(properties.getProperty("mongodb.url"));
    }

    @Bean
    public MongoDatabaseFactory mongoDbFactory(MongoClient mongoClient, @Qualifier("configProperties") Properties properties) {
        return new SimpleMongoClientDatabaseFactory(mongoClient, properties.getProperty("mongodb.database"));
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDbFactory) {
        return new MongoTemplate(mongoDbFactory);
    }
}
