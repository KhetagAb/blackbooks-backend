package com.tinkoff.web.blackbooks.server.controller;

import com.tinkoff.web.blackbooks.server.service.DiscoveryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "discovery")
public class DiscoveryController {

    private final DiscoveryService discoveryService;

    public DiscoveryController(DiscoveryService discoveryService) {
        this.discoveryService = discoveryService;
    }

    @GetMapping(value = "/discovery")
    public Mono<String> discover() {
        Flux<String> discovered = discoveryService.discoverAll();
        return discovered.collect(Collectors.joining(",", "{", "}"));
    }

}
