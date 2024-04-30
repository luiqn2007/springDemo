package com.example.mybank.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MyErrorHandler extends DefaultResponseErrorHandler {

    private static Logger LOGGER = LogManager.getLogger();

    @Override
    protected void handleError(ClientHttpResponse response,
                               HttpStatusCode statusCode) throws IOException {
        LOGGER.warn("Status code received from service: {}", statusCode);
        String body = StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8);
        LOGGER.warn("Response body: {}", body);
        super.handleError(response, statusCode);
    }
}
