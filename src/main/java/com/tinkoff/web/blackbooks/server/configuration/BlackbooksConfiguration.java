package com.tinkoff.web.blackbooks.server.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


// dzestelov: What is the difference with Configuration and SpringBootConfiguration?
// Why there should be only one SpringBootConfiguration class in the application?
@Configuration
public class BlackbooksConfiguration {

    @Bean
    public WebClient webClientBean() {
        return WebClient.create("http://localhost:8080");
    }

}
