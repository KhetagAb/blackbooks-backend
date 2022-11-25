package com.tinkoff.web.blackbooks.server.controller;

import com.tinkoff.web.blackbooks.server.domain.dao.respository.RepositoryInitializer;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "management.server.port=8080"
        }
)
public class BaseControllerTest {

    protected static Matcher<Object> UUID_MATCHER = new BaseMatcher<>() {
        @Override
        public void describeTo(Description description) {
            description.appendText("correct UUID");
        }

        @Override
        public boolean matches(Object actual) {
            try {
                UUID ignored = UUID.fromString((String) actual);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    };
    @Autowired
    protected WebTestClient testClient;
    @Autowired
    RepositoryInitializer initialStorage;

    protected static void expectUpStatus(WebTestClient.ResponseSpec exchange) {
        expectJson(exchange)
                .expectBody().json("{\"status\": \"UP\"}");
    }

    protected static WebTestClient.ResponseSpec expectJson(WebTestClient.ResponseSpec exchange) {
        return exchange.expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.APPLICATION_JSON);
    }

    protected WebTestClient.RequestHeadersSpec<?> getJson(String uri, Object... uriVariables) {
        return testClient.get()
                .uri(uri, uriVariables)
                .accept(MediaType.APPLICATION_JSON);
    }

    protected WebTestClient.RequestHeadersSpec<?> getJson(String uri) {
        return testClient.get()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON);
    }

    protected WebTestClient.RequestHeadersSpec<?> postJson(String uri, String json) {
        return testClient.post()
                .uri(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(json)
                .accept(MediaType.APPLICATION_JSON);
    }

    protected WebTestClient.RequestHeadersSpec<?> putJson(String json, String uri, Object... uriVariables) {
        return testClient.put()
                .uri(uri, uriVariables)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(json)
                .accept(MediaType.APPLICATION_JSON);
    }

    protected WebTestClient.RequestHeadersSpec<?> deleteJson(String uri, Object... uriVariables) {
        return testClient.delete()
                .uri(uri, uriVariables)
                .accept(MediaType.APPLICATION_JSON);
    }
}
