package com.example.mybank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@SpringBootApplication
public class ClientApp {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ClientApp.class);
        RestTemplate restTemplate = context.getBean(RestTemplate.class);
        getFixedDepositList(restTemplate);
        getFixedDepositList();
    }

    private static void getFixedDepositList(RestTemplate restTemplate) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");

        HttpEntity<List<FixedDepositDetails>> request = new HttpEntity<>(headers);
        ParameterizedTypeReference<List<FixedDepositDetails>> typeRef
                = new ParameterizedTypeReference<>() {};
        ResponseEntity<List<FixedDepositDetails>> response = restTemplate
                .exchange("http://localhost:8080/fixedDeposits",
                        HttpMethod.GET, request, typeRef);

        List<FixedDepositDetails> depositDetails = response.getBody();
        LOGGER.info("List of fixed deposit details: \n{}", depositDetails);
    }

    private static void getFixedDepositList() {
        WebClient webClient = WebClient.create("http://localhost:8080");
        webClient.get().uri("/fixedDeposits")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve() // 发送请求并接收相应
                .bodyToFlux(FixedDepositDetails.class)
                .subscribe(fdd -> LOGGER.info("getFixedDepositList: receive {}", fdd));
    }
}
