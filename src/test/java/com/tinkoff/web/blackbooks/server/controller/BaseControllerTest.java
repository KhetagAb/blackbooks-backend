package com.tinkoff.web.blackbooks.server.controller;

import com.tinkoff.web.blackbooks.server.domain.dao.entry.Entry;
import com.tinkoff.web.blackbooks.server.domain.dao.respository.RepositoryInitializer;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.UUID;

public abstract class BaseControllerTest<E extends Entry> extends BaseTest {

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
    RepositoryInitializer initialStorage;

    protected abstract String getControllerPathPrefix();

    protected abstract List<E> getStorageEntities();

    protected abstract String generateCorrectEntryJson();

    protected abstract void jsonEqual(WebTestClient.BodyContentSpec bodyContentSpec,
                                      String jsonPathPrefix,
                                      E entry);

    @Test
    void itShouldCreateEntry() {
        // given
        String entry = generateCorrectEntryJson();

        // when
        var response = postJson(getControllerPathPrefix() + "/create", entry).exchange();

        // then
        expectJson(response)
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$")
                .value(UUID_MATCHER);
    }

    @Test
    void itShouldGetEntry() {
        // given
        E entry = getStorageEntities().get(0);

        // when
        var response = getJson(getControllerPathPrefix() + "/{id}", entry.getId()).exchange();

        // then
        jsonEqual(expectJson(response).expectBody(), "$", entry);
    }

    @Test
    void itShouldGetAllEntries() {
        // given
        List<E> entities = getStorageEntities();

        // when
        var response = getJson(getControllerPathPrefix() + "/all").exchange();

        // then
        var expectBody = expectJson(response).expectBody();
        for (int i = 0; i < entities.size(); i++) {
            jsonEqual(expectBody, "$.[" + i + "]", entities.get(i));
        }
    }

    @Test
    void itShouldUpdateEntry() {
        // given
        E entry = getStorageEntities().get(0);
        String newUserProfile = generateCorrectEntryJson();

        // when
        var response = putJson(newUserProfile,
                getControllerPathPrefix() + "/{id}",
                entry.getId()).exchange();

        // then
        response.expectStatus().isOk();
    }

    @Test
    void itShouldDeleteEntry() {
        // given
        E entry = getStorageEntities().get(0);

        // when
        var response = deleteJson(getControllerPathPrefix() + "/{id}", entry.getId()).exchange();

        // then
        response.expectStatus().isOk();
    }
}
