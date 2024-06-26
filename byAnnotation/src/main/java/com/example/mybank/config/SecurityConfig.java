package com.example.mybank.config;

import com.example.mybank.handler.MyAuthFailureHandler;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.acls.AclPermissionCacheOptimizer;
import org.springframework.security.acls.AclPermissionEvaluator;
import org.springframework.security.acls.domain.*;
import org.springframework.security.acls.jdbc.BasicLookupStrategy;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.jdbc.LookupStrategy;
import org.springframework.security.acls.model.AclCache;
import org.springframework.security.acls.model.AclService;
import org.springframework.security.acls.model.PermissionGrantingStrategy;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.web.SecurityFilterChain;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableCaching
public class SecurityConfig {

    @Bean
    public SecurityExpressionHandler<MethodInvocation> securityExpressionHandler(AclService aclService) {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(new AclPermissionEvaluator(aclService));
        expressionHandler.setPermissionCacheOptimizer(new AclPermissionCacheOptimizer(aclService));
        return expressionHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
                                                   @Qualifier("securityDataSource") DataSource dataSource) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        JdbcDaoImpl userDetailsManager = new JdbcDaoImpl();
        userDetailsManager.setDataSource(dataSource);
        httpSecurity.userDetailsService(userDetailsManager);

        httpSecurity
                .exceptionHandling(configurer -> configurer
                        .accessDeniedHandler((request, response, accessDeniedException) -> response.sendRedirect("/access-denied")))
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/**").hasAnyAuthority("ROLE_CUSTOM", "ROLE_ADMIN")
                        .anyRequest().authenticated())
                .formLogin(login -> login.loginPage("/login").failureHandler(new MyAuthFailureHandler()))
                .logout(logout -> {})
                .rememberMe(rememberMe -> {})
                .headers(headers -> headers
                        .cacheControl(cache -> {})
                        .xssProtection(xss -> {}));

        return httpSecurity.build();
    }

    @Bean
    public AclAuthorizationStrategyImpl aclAuthorizationStrategy() {
        return new AclAuthorizationStrategyImpl(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
    }

    @Bean
    public DefaultPermissionGrantingStrategy permissionGrantingStrategy() {
        return new DefaultPermissionGrantingStrategy(new ConsoleAuditLogger());
    }

    @Bean
    public SpringCacheBasedAclCache aclCache(AclAuthorizationStrategy aclAuthorizationStrategy,
                                             PermissionGrantingStrategy permissionGrantingStrategy) throws IOException {
        ClassPathResource resource = new ClassPathResource("ehcache.xml");
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager manager = cachingProvider.getCacheManager(resource.getURI(), resource.getClassLoader());
        JCacheCacheManager jm = new JCacheCacheManager(manager);
        return new SpringCacheBasedAclCache(jm.getCache("myCache"), permissionGrantingStrategy, aclAuthorizationStrategy);
    }

    @Bean
    public BasicLookupStrategy lookupStrategy(@Qualifier("securityDataSource") DataSource dataSource,
                                              AclCache aclCache,
                                              AclAuthorizationStrategy aclAuthorizationStrategy,
                                              PermissionGrantingStrategy permissionGrantingStrategy) {
        return new BasicLookupStrategy(dataSource, aclCache, aclAuthorizationStrategy, permissionGrantingStrategy);
    }

    @Bean
    public JdbcMutableAclService aclService(DataSource dataSource, LookupStrategy lookupStrategy, AclCache aclCache) {
        return new JdbcMutableAclService(dataSource, lookupStrategy, aclCache);
    }
}