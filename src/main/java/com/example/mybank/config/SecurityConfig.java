package com.example.mybank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        httpSecurity.authorizeHttpRequests(request -> request
                        .requestMatchers("/**").hasAnyRole("ROLE_CUSTOM", "ROLE_ADMIN")
                        .anyRequest().authenticated())
                .logout(logout -> {
                })
                .rememberMe(rememberMe -> {
                })
                .headers(headers -> headers
                        .cacheControl(cache -> {
                        })
                        .xssProtection(xss -> {
                        }));

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(User
                .withUsername("admin")
                .password("{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzQvVCp5pe5WM2tDA.u")
                .authorities("ROLE_ADMIN")
                .build());
        userDetailsManager.createUser(User
                .withUsername("user1")
                .password("{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzQvVCp5pe5WM2tDA.u")
                .authorities("ROLE_CUSTOM")
                .build());
        userDetailsManager.createUser(User
                .withUsername("user2")
                .password("{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzQvVCp5pe5WM2tDA.u")
                .authorities("ROLE_CUSTOM")
                .build());

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsManager);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return new ProviderManager(provider);
    }
}