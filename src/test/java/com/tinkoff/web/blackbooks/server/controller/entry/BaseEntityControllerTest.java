package com.tinkoff.web.blackbooks.server.controller.entry;

import com.tinkoff.web.blackbooks.server.controller.JsonContentControllerTest;
import com.tinkoff.web.blackbooks.server.dao.entity.Entity;
import com.tinkoff.web.blackbooks.server.mock.RepositoryTestMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static com.tinkoff.web.blackbooks.server.TestUtils.UUID_MATCHER;

public abstract class BaseEntityControllerTest<E extends Entity> extends JsonContentControllerTest {

    @Autowired
    protected RepositoryTestMock mockDb;

    protected abstract List<E> getStorage();

    protected abstract String getControllerPathPrefix();

    protected abstract String generateCorrectEntryJson();

    protected abstract void jsonEqual(WebTestClient.BodyContentSpec bodyContentSpec,
                                      String jsonPathPrefix,
                                      E entry);

    @BeforeEach
    public void setup() {
        mockDb.resetAndInitializeDb();
    }

    @Test
    void itShouldCreateEntry() {
        // given
        String entry = generateCorrectEntryJson();

        // when
        var response = post(getControllerPathPrefix() + "/create", entry).exchange();

        // then
        expect(response)
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$")
                .value(UUID_MATCHER);
    }

    @Test
    void itShouldGetEntry() {
        // given
        E entry = getStorage().get(1);

        // when
        var response = get(getControllerPathPrefix() + "/{id}", entry.getId()).exchange();

        // then
        jsonEqual(expect(response).expectBody(), "$", entry);
    }

    @Test
    void itShouldGetAllEntries() {
        // given
        List<E> entities = getStorage();

        // when
        var response = get(getControllerPathPrefix() + "/all").exchange();

        // then
        var expectBody = expect(response).expectBody();
        for (int i = 0; i < entities.size(); i++) {
            jsonEqual(expectBody, "$.[" + i + "]", entities.get(i));
        }
    }

    @Test
    void itShouldUpdateEntry() {
        // given
        E entry = getStorage().get(0);
        String json = generateCorrectEntryJson();

        // when
        var response = put(json,
                getControllerPathPrefix() + "/{id}",
                entry.getId()).exchange();

        // then
        response.expectStatus().isOk();
    }

    @Test
    void itShouldDeleteEntry() {
        // given
        E entry = getStorage().get(0);

        // when
        var response = delete(getControllerPathPrefix() + "/{id}", entry.getId()).exchange();

        // then
        response.expectStatus().isOk();
    }
}
