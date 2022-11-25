package com.tinkoff.web.blackbooks.server.controller.entry;

import com.tinkoff.web.blackbooks.server.domain.dao.entry.DepositoryEntry;
import com.tinkoff.web.blackbooks.server.domain.dao.entry.TransactionEntry;
import com.tinkoff.web.blackbooks.server.domain.dao.entry.UserProfileEntry;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.UUID;

public class TransactionsControllerTest extends BaseEntryControllerTest<TransactionEntry> {
    @Override
    protected String getControllerPathPrefix() {
        return "/transactions";
    }

    @Override
    protected List<TransactionEntry> getStorage() {
        return repositoryTestMock.getTransactionEntries();
    }

    @Override
    protected String generateCorrectEntryJson() {
        UserProfileEntry profileEntry = repositoryTestMock.getUserProfileEntries().get(0);
        DepositoryEntry depositoryEntry = repositoryTestMock.getDepositoryEntries().get(0);
        return String.format("{\"nick\": \"%s\",\"shelf\": \"%s\"," +
                        "\"time\": \"0\",\"action\": \"%s\"}",
                profileEntry.getNick(), depositoryEntry.getAddress(), UUID.randomUUID());
    }

    @Override
    protected void jsonEqual(WebTestClient.BodyContentSpec bodyContentSpec, String jsonPathPrefix, TransactionEntry entry) {
        bodyContentSpec
                .jsonPath(jsonPathPrefix + ".nick").isEqualTo(entry.getUser().getNick())
                .jsonPath(jsonPathPrefix + ".shelf").isEqualTo(entry.getDepository().getAddress())
                .jsonPath(jsonPathPrefix + ".action").isEqualTo(entry.getAction());
    }
}
