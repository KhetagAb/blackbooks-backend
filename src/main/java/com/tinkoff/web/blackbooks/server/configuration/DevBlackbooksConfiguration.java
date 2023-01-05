package com.tinkoff.web.blackbooks.server.configuration;

import com.tinkoff.web.blackbooks.server.settings.ServicesSettings;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.client.WebClient;


// dzestelov: What is the difference with Configuration and SpringBootConfiguration?
// Why there should be only one SpringBootConfiguration class in the application?
@Configuration
@EnableConfigurationProperties(ServicesSettings.class)
public class DevBlackbooksConfiguration {

    @Bean
    public WebClient webClientBean(Environment env) {
        return WebClient.create("http://localhost:" + env.getProperty("server.port"));
    }

}
