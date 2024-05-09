package com.example.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableWebFlux
@EnableWebFluxSecurity
public class WebFluxApplication implements WebFluxConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(WebFluxApplication.class, args);
        WebClient webClient = WebClient.builder()
                .filter(ExchangeFilterFunctions.basicAuthentication("user", "pwd"))
                .build();
    }

}
