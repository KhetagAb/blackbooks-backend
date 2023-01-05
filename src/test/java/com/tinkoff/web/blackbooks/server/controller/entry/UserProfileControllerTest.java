package com.tinkoff.web.blackbooks.server.controller.entry;

import com.tinkoff.web.blackbooks.server.dao.entity.UserProfileEntity;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.UUID;

class UserProfileControllerTest extends BaseEntityControllerTest<UserProfileEntity> {

    @Override
    protected String generateCorrectEntryJson() {
        return String.format(
                "{\"nick\": \"%s\",\"name\": \"%s\"," +
                        "\"age\": \"%s\",\"gender\": \"%s\",\"location\": \"%s\"}",
                UUID.randomUUID(), UUID.randomUUID(),
                0, UUID.randomUUID(), "0 0");
    }

    @Override
    protected String getControllerPathPrefix() {
        return "/user";
    }

    @Override
    protected List<UserProfileEntity> getStorage() {
        return mockDb.getUserProfileEntries();
    }

    @Override
    protected void jsonEqual(WebTestClient.BodyContentSpec bodyContentSpec, String jsonPathPrefix, UserProfileEntity entry) {
        bodyContentSpec
                .jsonPath(jsonPathPrefix + ".nick").isEqualTo(entry.getNick())
                .jsonPath(jsonPathPrefix + ".name").isEqualTo(entry.getName())
                .jsonPath(jsonPathPrefix + ".age").isEqualTo(entry.getAge())
                .jsonPath(jsonPathPrefix + ".gender").isEqualTo(entry.getGender())
                .jsonPath(jsonPathPrefix + ".location").isEqualTo(entry.getLatitude() + " " + entry.getLongitude());
    }
}