package com.tinkoff.web.blackbooks.server.discovery;

import com.tinkoff.web.blackbooks.server.settings.ServicesSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URL;

@Component
@RequiredArgsConstructor
public class DefaultServiceDiscovery implements ServiceDiscovery {

    private final static String ERROR_MSG = "{ \"message\": \"is not available\" }";
    private final ServicesSettings settings;

    @Override
    public Mono<String> discoverService(URL url) {
        WebClient client = WebClient.create(url.toString());

        try {
            Mono<String> version = client.get()
                    .uri(settings.getVersion())
                    .retrieve()
                    .bodyToMono(String.class);
            return client.get()
                    .uri(settings.getLiveliness())
                    .retrieve()
                    .bodyToMono(String.class)
                    .zipWith(version, (v, s) -> v.contains("UP") ? s : ERROR_MSG)
                    .onErrorReturn(ERROR_MSG);
        } catch (Exception e) {
            return Mono.just(ERROR_MSG);
        }
    }
}
