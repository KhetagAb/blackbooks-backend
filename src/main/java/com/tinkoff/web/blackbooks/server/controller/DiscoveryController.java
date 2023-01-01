package com.tinkoff.web.blackbooks.server.controller;

import com.tinkoff.web.blackbooks.server.service.DiscoveryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.net.MalformedURLException;

@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "discovery")
@RequiredArgsConstructor
public class DiscoveryController {

    private final DiscoveryService discoveryService;

    @GetMapping(value = "/discovery")
    public Flux<String> discover() {
        try {
            return discoveryService.discoverAll();
        } catch (MalformedURLException e) {
            return Flux.error(e);
        }
    }

}
