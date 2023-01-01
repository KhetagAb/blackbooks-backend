package com.tinkoff.web.blackbooks.server.service;

import com.tinkoff.web.blackbooks.server.discovery.ServiceDiscovery;
import com.tinkoff.web.blackbooks.server.settings.ServicesSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class DiscoveryService {

    private final ServiceDiscovery serviceDiscovery;
    private final ServicesSettings settings;

    public Flux<String> discoverAll() throws MalformedURLException {
        return Flux.fromIterable(settings.getServices().values())
                .flatMap(service -> {
                    try {
                        URL livelinessPath = service.getLivelinessPath();
                        String response = serviceDiscovery.discoverService(livelinessPath.toURI());

                        if (response != null && response.contains("UP")) {
                            return Mono.just(service);
                        }
                    } catch (MalformedURLException | URISyntaxException ignored) {
                        // toDo: add logs
                    }

                    return Mono.empty();
                })
                .flatMap(service -> {
                    try {
                        URL versionUrl = service.getVersionUrl();
                        String response = serviceDiscovery.discoverService(versionUrl.toURI());
                        return Mono.just(response);
                    } catch (MalformedURLException | URISyntaxException e) {
                        return Mono.error(e);
                    }
                })
                .publishOn(Schedulers.newParallel("parallel", 5));
    }
}
