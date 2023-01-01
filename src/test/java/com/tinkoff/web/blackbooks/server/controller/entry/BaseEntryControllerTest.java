package com.tinkoff.web.blackbooks.server.controller.entry;

import com.tinkoff.web.blackbooks.server.mock.RepositoryTestMock;
import com.tinkoff.web.blackbooks.server.controller.JsonContentControllerTest;
import com.tinkoff.web.blackbooks.server.domain.dao.entry.Entry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static com.tinkoff.web.blackbooks.server.TestUtils.UUID_MATCHER;

public abstract class BaseEntryControllerTest<E extends Entry> extends JsonContentControllerTest {

    @Autowired
    protected RepositoryTestMock repositoryTestMock;

    protected abstract List<E> getStorage();

    protected abstract String getControllerPathPrefix();

    protected abstract String generateCorrectEntryJson();

    protected abstract void jsonEqual(WebTestClient.BodyContentSpec bodyContentSpec,
                                      String jsonPathPrefix,
                                      E entry);

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
        E entry = getStorage().get(0);

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
        String newUserProfile = generateCorrectEntryJson();

        // when
        var response = put(newUserProfile,
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
