package com.example.mybank.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public SimpleCacheManager myCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(
                Set.of(new ConcurrentMapCache("fixedDepositList"), new ConcurrentMapCache("fixedDeposit")));
        return cacheManager;
    }
}
