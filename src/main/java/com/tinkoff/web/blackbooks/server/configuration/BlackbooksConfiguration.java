package com.tinkoff.web.blackbooks.server.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class BlackbooksConfiguration {

    @Bean
    public WebClient webClientBean() {
        return WebClient.create("http://localhost:8080");
    }

}
