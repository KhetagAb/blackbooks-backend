package com.tinkoff.web.blackbooks.server.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/system")
@Tag(name = "system", description = "system endpoint of the blackbooks application")
public class SystemController {

    private final WebClient client;

    private SystemController(WebClient client) {
        this.client = client;
    }

    @GetMapping("liveness")
    public Mono<String> getLiveness() {
        return getStringResponseMonoByUri("/actuator/health/liveness");
    }

    @GetMapping("readiness")
    public Mono<String> getReadiness() {
        return getStringResponseMonoByUri("/actuator/health/readiness");
    }

    @GetMapping("version")
    public Mono<String> getVersion() {
        return getStringResponseMonoByUri("/actuator/info");
    }

    private Mono<String> getStringResponseMonoByUri(String uri) {
        return client.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class);
    }
}
