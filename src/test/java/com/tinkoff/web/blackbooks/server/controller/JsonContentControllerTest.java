package com.tinkoff.web.blackbooks.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {
                "management.server.port=8070"
        }
)
@ActiveProfiles("test")
public class JsonContentControllerTest {

    @Autowired
    protected WebTestClient testClient;

    protected static void expectUpStatus(WebTestClient.ResponseSpec exchange) {
        expect(exchange)
                .expectBody().json("{\"status\": \"UP\"}");
    }

    protected static WebTestClient.ResponseSpec expect(WebTestClient.ResponseSpec exchange) {
        return exchange.expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.APPLICATION_JSON);
    }

    protected WebTestClient.RequestHeadersSpec<?> get(String uri, Object... uriVariables) {
        return testClient.get()
                .uri(uri, uriVariables)
                .accept(MediaType.APPLICATION_JSON);
    }

    protected WebTestClient.RequestHeadersSpec<?> get(String uri) {
        return testClient.get()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON);
    }

    protected WebTestClient.RequestHeadersSpec<?> post(String uri, String json) {
        return testClient.post()
                .uri(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(json)
                .accept(MediaType.APPLICATION_JSON);
    }

    protected WebTestClient.RequestHeadersSpec<?> put(String json, String uri, Object... uriVariables) {
        return testClient.put()
                .uri(uri, uriVariables)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(json)
                .accept(MediaType.APPLICATION_JSON);
    }

    protected WebTestClient.RequestHeadersSpec<?> delete(String uri, Object... uriVariables) {
        return testClient.delete()
                .uri(uri, uriVariables)
                .accept(MediaType.APPLICATION_JSON);
    }
}
