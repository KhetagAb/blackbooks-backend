package com.tinkoff.web.blackbooks.server.controller.entry;

import com.tinkoff.web.blackbooks.server.dao.entity.DepositoryEntity;
import com.tinkoff.web.blackbooks.server.dao.entity.TransactionEntity;
import com.tinkoff.web.blackbooks.server.dao.entity.UserProfileEntity;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.UUID;

public class TransactionsControllerTest extends BaseEntityControllerTest<TransactionEntity> {
    @Override
    protected String getControllerPathPrefix() {
        return "/transactions";
    }

    @Override
    protected List<TransactionEntity> getStorage() {
        return repositoryTestMock.getTransactionEntries();
    }

    @Override
    protected String generateCorrectEntryJson() {
        UserProfileEntity profileEntry = repositoryTestMock.getUserProfileEntries().get(0);
        DepositoryEntity depositoryEntry = repositoryTestMock.getDepositoryEntries().get(0);
        return String.format("{\"nick\": \"%s\",\"shelf\": \"%s\"," +
                        "\"time\": \"0\",\"action\": \"%s\"}",
                profileEntry.getNick(), depositoryEntry.getAddress(), UUID.randomUUID());
    }

    @Override
    protected void jsonEqual(WebTestClient.BodyContentSpec bodyContentSpec, String jsonPathPrefix, TransactionEntity entry) {
//        bodyContentSpec
//                .jsonPath(jsonPathPrefix + ".nick").isEqualTo(entry.getNick())
//                .jsonPath(jsonPathPrefix + ".shelf").isEqualTo(entry.getDepository().getAddress())
//                .jsonPath(jsonPathPrefix + ".action").isEqualTo(entry.getAction()); // toDo
    }
}
