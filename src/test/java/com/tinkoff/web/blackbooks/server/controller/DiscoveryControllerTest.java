package com.tinkoff.web.blackbooks.server.controller;

import org.junit.jupiter.api.Test;

class DiscoveryControllerTest extends JsonContentControllerTest {

    @Test
    void itShouldReturnDiscoveredServices() {
        // given

        // when
        var exchange = get("/api/discovery").exchange();

        // then
        expect(exchange)
                .expectBody()
                .jsonPath("$.bookhunter").hasJsonPath()
                .jsonPath("$.bookshelf").hasJsonPath();
    }
}