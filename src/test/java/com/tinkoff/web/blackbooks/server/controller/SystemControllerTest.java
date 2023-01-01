package com.tinkoff.web.blackbooks.server.controller;

import org.junit.jupiter.api.Test;

public class SystemControllerTest extends JsonContentControllerTest {

    @Test
    void livenessProbeShouldBeAlive() {
        // when
        var exchange = get("/system/liveness").exchange();

        // then
        expectUpStatus(exchange);
    }

    @Test
    void readinessProbeShouldBeAlive() {
        // when
        var exchange = get("/system/readiness").exchange();

        // then
        expectUpStatus(exchange);
    }

    @Test
    void versionProbeShouldBeAlive() {
        // when
        var exchange = get("/system/version").exchange();

        // then
        expect(exchange)
                .expectBody()
                .jsonPath("$.build").hasJsonPath()
                .jsonPath("$.build.artifact").isEqualTo("blackbooks")
                .jsonPath("$.build.name").isEqualTo("blackbooks")
                .jsonPath("$.build.group").isEqualTo("com.tinkoff.books.web");
    }
}
