package com.tinkoff.web.blackbooks.server.service;

import com.tinkoff.web.blackbooks.server.discovery.ServiceDiscovery;
import com.tinkoff.web.blackbooks.server.settings.ServicesSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DiscoveryService {

    private final ServiceDiscovery serviceDiscovery;
    private final ServicesSettings settings;

    public Flux<String> discoverAll() {
        return Flux.fromIterable(settings.getServices().entrySet())
                .flatMap(serviceEntry -> {
                    var serviceName = serviceEntry.getKey();
                    var service = serviceEntry.getValue();
                    Mono<String> result = serviceDiscovery.discoverService(service.getUrl());
                    return result.map(msg -> String.format("\"%s\": %s", serviceName, msg));
                });
    }
}
