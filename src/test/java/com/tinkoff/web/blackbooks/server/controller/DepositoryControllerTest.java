package com.tinkoff.web.blackbooks.server.controller;

import com.tinkoff.web.blackbooks.server.domain.dao.entry.DepositoryEntry;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

class DepositoryControllerTest extends BaseControllerTest {

    public static String generateRandomDepositoryJson() {
        return String.format(
                "{\"nick\": \"%s\",\"address\": \"%s\"," +
                        "\"description\": \"%s\",\"type\": \"%s\",\"location\": \"%s\"}",
                UUID.randomUUID(), UUID.randomUUID(),
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
    }

    @Test
    void itShouldCreateDepository() {
        // given
        String depository = generateRandomDepositoryJson();

        // when
        var response = postJson("/depository/create", depository).exchange();

        // then
        expectJson(response)
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$")
                .value(UUID_MATCHER);
    }


    @Test
    void itShouldGetDepository() {
        // given
        DepositoryEntry depository = initialStorage.getDepositoryEntries().get(0);

        // when
        var response = getJson("/depository/{id}", depository.getId()).exchange();

        // then
        expectJson(response)
                .expectBody()
                .jsonPath("$.nick").isEqualTo(depository.getNick())
                .jsonPath("$.address").isEqualTo(depository.getAddress())
                .jsonPath("$.type").isEqualTo(depository.getType())
                .jsonPath("$.description").isEqualTo(depository.getDescription())
                .jsonPath("$.location").isEqualTo(depository.getLocation());
    }

    @Test
    void itShouldGetAllDepositories() {
        // given
        List<DepositoryEntry> depositories = initialStorage.getDepositoryEntries();

        // when
        var response = getJson("/depository/all").exchange();

        // then
        var expectBody = expectJson(response).expectBody();
        for (int i = 0; i < depositories.size(); i++) {
            DepositoryEntry entry = depositories.get(i);
            String prefix = "$.[" + i + "].";
            expectBody
                    .jsonPath(prefix + "nick").isEqualTo(entry.getNick())
                    .jsonPath(prefix + "address").isEqualTo(entry.getAddress())
                    .jsonPath(prefix + "type").isEqualTo(entry.getType())
                    .jsonPath(prefix + "description").isEqualTo(entry.getDescription())
                    .jsonPath(prefix + "location").isEqualTo(entry.getLocation());
        }
    }

    @Test
    void itShouldUpdateDepository() {
        // given
        DepositoryEntry depository = initialStorage.getDepositoryEntries().get(0);
        String newDepository = generateRandomDepositoryJson();


        // when
        var response = putJson(newDepository,
                "/depository/{id}",
                depository.getId()).exchange();

        // then
        response.expectStatus().isOk();
    }

    @Test
    void deleteUser() {
        // given
        DepositoryEntry depository = initialStorage.getDepositoryEntries().get(0);

        // when
        var response = deleteJson("/depository/{id}", depository.getId()).exchange();

        // then
        response.expectStatus().isOk();
    }
}