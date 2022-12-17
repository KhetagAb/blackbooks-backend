package com.tinkoff.web.blackbooks.server.service;

import com.tinkoff.web.blackbooks.server.discovery.ServiceDiscovery;
import com.tinkoff.web.blackbooks.server.settings.ServicesSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.net.MalformedURLException;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class DiscoveryService {

    private final ServiceDiscovery serviceDiscovery;
    private final ServicesSettings settings;

    public Flux<String> discoverAll() {
        return Flux.create(emitter -> {
            try {
                URL hunterDiscoveryUrl = settings.getBookHunterDiscoveryUrl();
                URL shelfDiscoveryUrl = settings.getBookShelfDiscoveryUrl();

                emitter.next(serviceDiscovery.discoverService(hunterDiscoveryUrl));
                emitter.next(serviceDiscovery.discoverService(shelfDiscoveryUrl));
            } catch (MalformedURLException e) {
                emitter.error(e);
            }
        });
    }
}
