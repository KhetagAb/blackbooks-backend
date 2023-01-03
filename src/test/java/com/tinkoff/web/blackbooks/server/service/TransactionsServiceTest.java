package com.tinkoff.web.blackbooks.server.service;

import com.tinkoff.web.blackbooks.server.controller.util.SortType;
import com.tinkoff.web.blackbooks.server.dao.entity.DepositoryEntity;
import com.tinkoff.web.blackbooks.server.dao.entity.TransactionEntity;
import com.tinkoff.web.blackbooks.server.dao.entity.UserProfileEntity;
import com.tinkoff.web.blackbooks.server.domain.dto.TransactionDto;
import com.tinkoff.web.blackbooks.server.mock.RepositoryTestMock;
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
    RepositoryTestMock initializer;

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
        DepositoryEntity depository = initializer.getDepositoryEntries().get(0);
        UserProfileEntity user = initializer.getUserProfileEntries().get(0);
        TransactionDto transactionDto = new TransactionDto(user.getNick(),
                depository.getLatitude() + " " + depository.getLongitude(), 0, "");

        // when
        UUID uuid = transactionsService.create(transactionDto);

        // then
        assertTrue(UUID_MATCHER.matches(uuid));
    }

    @Test
    void itShouldGetTransaction() {
        // given
        TransactionEntity transaction = initializer.getTransactionEntries().get(0);
        UUID id = transaction.getId();

        // when
        TransactionDto transactionDto = transactionsService.get(id).block();

        // then
        assertNotNull(transactionDto);
//        assertEquals(transactionDto.nick(), transaction.getUser().getNick()); // toDo equals for entity and dto
//        assertEquals(transactionDto.shelf(), transaction.getDepository().getAddress());
//        assertEquals(transactionDto.time(), transaction.getTime().getTime());
//        assertEquals(transactionDto.action(), transaction.getAction());
    }

    @Test
    void itShouldGetEmptyResponse() {
        // given
        DepositoryEntity depositoryEntity = initializer.getDepositoryEntries().get(0);
        UserProfileEntity userProfileEntity = initializer.getUserProfileEntries().get(0);


        // when
        Flux<TransactionDto> transactionDto = transactionsService.getTransactions(depositoryEntity.getId(),
                userProfileEntity.getId(), 0, SortType.DESC);

        // then
        List<TransactionDto> list = transactionDto.toStream().toList();
        assertTrue(list.isEmpty());
    }

    @Test
    void itShouldGetSelectedTransactions() {
        // given
        DepositoryEntity depositoryEntity = initializer.getDepositoryEntries().get(0);
        UserProfileEntity userProfileEntity = initializer.getUserProfileEntries().get(0);
        TransactionEntity entity = initializer.getTransactionEntries().get(3);

        // when
        Flux<TransactionDto> transactionDto = transactionsService.getTransactions(depositoryEntity.getId(),
                userProfileEntity.getId(), Integer.MAX_VALUE, SortType.DESC);

        // then
        List<TransactionDto> list = transactionDto.toStream().toList();
        assertEquals(list.size(), 4);

        TransactionDto dto = list.get(0);
//        assertEquals(dto.nick(), entity.getUser().getNick()); // toDo the same
//        assertEquals(dto.shelf(), entity.getDepository().getAddress());
//        assertEquals(dto.time(), entity.getTime().getTime());
//        assertEquals(dto.action(), entity.getAction());
    }
}
