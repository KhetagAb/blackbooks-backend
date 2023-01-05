package com.tinkoff.web.blackbooks.server.discovery;

import reactor.core.publisher.Mono;

import java.net.URL;

public interface ServiceDiscovery {

    Mono<String> discoverService(URL url);
}
