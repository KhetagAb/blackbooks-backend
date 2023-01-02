package com.tinkoff.web.blackbooks.server.discovery;

import com.tinkoff.web.blackbooks.server.settings.ServicesSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URL;

@Component
@RequiredArgsConstructor
public class DefaultServiceDiscovery implements ServiceDiscovery {

    private final ServicesSettings settings;

    @Override
    public String discoverService(URL url) {
        WebClient client = WebClient.create(url.toString());

        String response = getPathResponse(client, settings.getLiveliness());
        if (response != null && response.contains("UP")) {
            return getPathResponse(client, settings.getVersion());
        } else {
            return "{ message: \"is not available\" }";
        }
    }

    private static String getPathResponse(WebClient client, String path) {
        try {
            return client.get()
                    .uri(path)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (Exception e) {
            // toDo add log
            return null;
        }
    }
}
