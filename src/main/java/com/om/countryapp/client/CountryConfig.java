package com.om.countryapp.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class CountryConfig {

    @Value("${countryservice.base.url}")
    private String countryBaseUrl;

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl(countryBaseUrl).build();
    }
}
