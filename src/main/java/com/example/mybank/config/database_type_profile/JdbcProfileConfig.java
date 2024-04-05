package com.example.mybank.config.database_type_profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("jdbc")
public class JdbcProfileConfig {
}
