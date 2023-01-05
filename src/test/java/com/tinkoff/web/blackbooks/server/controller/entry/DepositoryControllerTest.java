package com.tinkoff.web.blackbooks.server.controller.entry;

import com.tinkoff.web.blackbooks.server.dao.entity.DepositoryEntity;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.UUID;

class DepositoryControllerTest extends BaseEntityControllerTest<DepositoryEntity> {

    @Override
    public String generateCorrectEntryJson() {
        return String.format(
                "{\"nick\": \"%s\",\"address\": \"%s\"," +
                        "\"description\": \"%s\",\"type\": \"%s\",\"location\": \"%s\"}",
                UUID.randomUUID(), UUID.randomUUID(),
                UUID.randomUUID(), UUID.randomUUID(), "0 0");
    }

    @Override
    protected void jsonEqual(WebTestClient.BodyContentSpec bodyContentSpec, String jsonPathPrefix, DepositoryEntity entry) {
        bodyContentSpec
                .jsonPath(jsonPathPrefix + ".nick").isEqualTo(entry.getName()) // toDo refactor - high dependency
                .jsonPath(jsonPathPrefix + ".address").isEqualTo(entry.getAddress())
                .jsonPath(jsonPathPrefix + ".type").isEqualTo(entry.getType())
                .jsonPath(jsonPathPrefix + ".description").isEqualTo(entry.getDescription())
                .jsonPath(jsonPathPrefix + ".location").isEqualTo(entry.getLatitude() + " " + entry.getLongitude());
    }

    @Override
    protected String getControllerPathPrefix() {
        return "/depository";
    }

    @Override
    protected List<DepositoryEntity> getStorage() {
        return mockDb.getDepositoryEntries();
    }
}