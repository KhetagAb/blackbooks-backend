package com.tinkoff.web.blackbooks.server.discovery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class DefaultServiceDiscovery implements ServiceDiscovery {

    private final WebClient client;

    @Override
    public String discoverService(URI uri) {
        return client.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
