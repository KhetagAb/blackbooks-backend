package com.tinkoff.web.blackbooks.server.controller;

import com.tinkoff.web.blackbooks.server.domain.dao.entry.UserProfileEntry;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.UUID;

class UserProfileControllerTest extends BaseControllerTest<UserProfileEntry> {

    @Override
    protected String generateCorrectEntryJson() {
        return String.format(
                "{\"nick\": \"%s\",\"name\": \"%s\"," +
                        "\"age\": \"%s\",\"gender\": \"%s\",\"location\": \"%s\"}",
                UUID.randomUUID(), UUID.randomUUID(),
                0, UUID.randomUUID(), UUID.randomUUID());
    }

    @Override
    protected String getControllerPathPrefix() {
        return "/user";
    }

    @Override
    protected List<UserProfileEntry> getStorageEntities() {
        return initialStorage.getUserProfileEntries();
    }

    @Override
    protected void jsonEqual(WebTestClient.BodyContentSpec bodyContentSpec, String jsonPathPrefix, UserProfileEntry entry) {
        bodyContentSpec
                .jsonPath(jsonPathPrefix + ".nick").isEqualTo(entry.getNick())
                .jsonPath(jsonPathPrefix + ".name").isEqualTo(entry.getName())
                .jsonPath(jsonPathPrefix + ".age").isEqualTo(entry.getAge())
                .jsonPath(jsonPathPrefix + ".gender").isEqualTo(entry.getGender())
                .jsonPath(jsonPathPrefix + ".location").isEqualTo(entry.getLocation());
    }
}