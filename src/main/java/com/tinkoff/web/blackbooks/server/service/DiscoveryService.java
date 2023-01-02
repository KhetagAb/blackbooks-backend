package com.tinkoff.web.blackbooks.server.service;

import com.tinkoff.web.blackbooks.server.discovery.ServiceDiscovery;
import com.tinkoff.web.blackbooks.server.settings.ServicesSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.net.MalformedURLException;

@Service
@RequiredArgsConstructor
public class DiscoveryService {

    private final ServiceDiscovery serviceDiscovery;
    private final ServicesSettings settings;

    public Flux<String> discoverAll() throws MalformedURLException {
        return Flux.fromIterable(settings.getServices().entrySet())
                .flatMap(serviceEntry -> {
                    var serviceName = serviceEntry.getKey();
                    var service = serviceEntry.getValue();
                    String result = serviceDiscovery.discoverService(service.getUrl());
                    return Mono.just(String.format("\"%s\": %s", serviceName, result));
                })
                .publishOn(Schedulers.newParallel("parallel", 5));
    }
}
