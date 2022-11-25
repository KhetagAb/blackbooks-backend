package com.tinkoff.web.blackbooks.server.controller.entry;

import com.tinkoff.web.blackbooks.server.domain.dao.entry.DepositoryEntry;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.UUID;

class DepositoryControllerTest extends BaseEntryControllerTest<DepositoryEntry> {

    @Override
    public String generateCorrectEntryJson() {
        return String.format(
                "{\"nick\": \"%s\",\"address\": \"%s\"," +
                        "\"description\": \"%s\",\"type\": \"%s\",\"location\": \"%s\"}",
                UUID.randomUUID(), UUID.randomUUID(),
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
    }

    @Override
    protected void jsonEqual(WebTestClient.BodyContentSpec bodyContentSpec, String jsonPathPrefix, DepositoryEntry entry) {
        bodyContentSpec
                .jsonPath(jsonPathPrefix + ".nick").isEqualTo(entry.getNick())
                .jsonPath(jsonPathPrefix + ".address").isEqualTo(entry.getAddress())
                .jsonPath(jsonPathPrefix + ".type").isEqualTo(entry.getType())
                .jsonPath(jsonPathPrefix + ".description").isEqualTo(entry.getDescription())
                .jsonPath(jsonPathPrefix + ".location").isEqualTo(entry.getLocation());
    }

    @Override
    protected String getControllerPathPrefix() {
        return "/depository";
    }

    @Override
    protected List<DepositoryEntry> getStorage() {
        return initialStorage.getDepositoryEntries();
    }
}