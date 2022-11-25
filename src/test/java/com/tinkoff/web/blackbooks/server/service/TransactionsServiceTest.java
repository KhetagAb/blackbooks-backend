package com.tinkoff.web.blackbooks.server.service;

import com.tinkoff.web.blackbooks.server.controller.util.SortType;
import com.tinkoff.web.blackbooks.server.domain.dao.dto.TransactionDto;
import com.tinkoff.web.blackbooks.server.domain.dao.entry.DepositoryEntry;
import com.tinkoff.web.blackbooks.server.domain.dao.entry.TransactionEntry;
import com.tinkoff.web.blackbooks.server.domain.dao.entry.UserProfileEntry;
import com.tinkoff.web.blackbooks.server.domain.dao.respository.RepositoryInitializer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static com.tinkoff.web.blackbooks.server.TestUtils.UUID_MATCHER;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransactionsServiceTest {

    @Autowired
    TransactionsService transactionsService;

    @Autowired
    RepositoryInitializer initializer;

    @Test
    void itShouldThrowException() {
        // given
        TransactionDto transactionDto = new TransactionDto("", "", 0, "");

        // when
        Executable exec = () -> transactionsService.create(transactionDto);

        // then
        assertThrows(NoSuchElementException.class, exec);
    }

    @Test
    void itShouldAddTransaction() {
        // given
        DepositoryEntry depository = initializer.getDepositoryEntries().get(0);
        UserProfileEntry user = initializer.getUserProfileEntries().get(0);
        TransactionDto transactionDto = new TransactionDto(user.getNick(), depository.getAddress(), 0, "");

        // when
        UUID uuid = transactionsService.create(transactionDto);

        // then
        assertTrue(UUID_MATCHER.matches(uuid));
    }

    @Test
    void itShouldGetTransaction() {
        // given
        TransactionEntry transaction = initializer.getTransactionEntries().get(0);
        UUID id = transaction.getId();

        // when
        TransactionDto transactionDto = transactionsService.get(id).block();

        // then
        assertNotNull(transactionDto);
        assertEquals(transactionDto.nick(), transaction.getUser().getNick());
        assertEquals(transactionDto.shelf(), transaction.getDepository().getAddress());
        assertEquals(transactionDto.time(), transaction.getTime().getTime());
        assertEquals(transactionDto.action(), transaction.getAction());
    }

    @Test
    void itShouldGetEmptyResponse() {
        // given
        DepositoryEntry depositoryEntry = initializer.getDepositoryEntries().get(0);
        UserProfileEntry userProfileEntry = initializer.getUserProfileEntries().get(0);


        // when
        Flux<TransactionDto> transactionDto = transactionsService.getTransactions(depositoryEntry.getId(),
                userProfileEntry.getId(), 0, SortType.DESC);

        // then
        List<TransactionDto> list = transactionDto.toStream().toList();
        assertTrue(list.isEmpty());
    }

    @Test
    void itShouldGetSelectedTransactions() {
        // given
        DepositoryEntry depositoryEntry = initializer.getDepositoryEntries().get(0);
        UserProfileEntry userProfileEntry = initializer.getUserProfileEntries().get(0);


        // when
        Flux<TransactionDto> transactionDto = transactionsService.getTransactions(depositoryEntry.getId(),
                userProfileEntry.getId(), Integer.MAX_VALUE, SortType.DESC);

        // then
        List<TransactionDto> list = transactionDto.toStream().toList();
        assertTrue(list.isEmpty());
    }
}
