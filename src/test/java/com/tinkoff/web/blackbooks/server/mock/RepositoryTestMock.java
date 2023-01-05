package com.tinkoff.web.blackbooks.server.mock;

import com.tinkoff.web.blackbooks.server.dao.entity.*;
import com.tinkoff.web.blackbooks.server.dao.respository.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
@Getter
public class RepositoryTestMock { // toDo temp class

    private final List<UserProfileEntity> userProfileEntries;

    private final List<DepositoryEntity> depositoryEntries;

    private final List<TransactionEntity> transactionEntries;

    // toDo: refactor with Spring Test Containers - init data
    private RepositoryTestMock(@Autowired UserProfileRepository userProfileRepository,
                               @Autowired DepositoryRepository depositoryRepository,
                               @Autowired TransactionRepository transactionRepository,
                               @Autowired BookMetaRepository bookMetaRepository,
                               @Autowired BookRepository bookRepository) {
        transactionRepository.deleteAll();
        userProfileRepository.deleteAll();
        depositoryRepository.deleteAll();
        bookMetaRepository.deleteAll();
        bookRepository.deleteAll();

        UserProfileEntity khetag = userProfileRepository.save(new UserProfileEntity(UUID.randomUUID(), "KhetagDz", "Khetag", 20, "male", 0, 0, Set.of(), Set.of()));
        UserProfileEntity ivan = userProfileRepository.save(new UserProfileEntity(null, "IvanM3239", "Ivan", 19, "male", 0, 2, Set.of(), Set.of()));
        UserProfileEntity defuser = userProfileRepository.save(new UserProfileEntity(null, "string", "string", 0, "female", 0, 2, Set.of(), Set.of()));

        userProfileEntries = List.of(khetag, ivan, defuser);

        DepositoryEntity itmo = depositoryRepository.save(new DepositoryEntity(null, "ITMO library",
                "St. Petersburg",
                "enter in the front of the building",
                "bookshelf",
                0,
                0, Set.of(), Set.of()));

        DepositoryEntity mipt = depositoryRepository.save(new DepositoryEntity(null, "MIPT library",
                "Moscow",
                "enter in the front of the building",
                "bookshelf",
                0,
                0, Set.of(), Set.of()));

        DepositoryEntity defdep = depositoryRepository.save(new DepositoryEntity(null, "string",
                "string",
                "string",
                "string",
                0,
                0, Set.of(), Set.of()));

        depositoryEntries = List.of(itmo, mipt, defdep);

        BookMetaEntity bookMetaEntity = new BookMetaEntity(null,
                "java",
                "how to wrote bad mock and refactor in in the futire",
                "Khetag");

        bookMetaEntity = bookMetaRepository.save(bookMetaEntity);

        BookEntity khetagItmoBook = new BookEntity(null, bookMetaEntity, khetag, itmo);
        khetagItmoBook = bookRepository.save(khetagItmoBook);

        BookEntity khetagMiptBook = new BookEntity(null, bookMetaEntity, khetag, mipt);
        khetagMiptBook = bookRepository.save(khetagMiptBook);

        BookEntity ivanItmoBook = new BookEntity(null, bookMetaEntity, ivan, itmo);
        ivanItmoBook = bookRepository.save(ivanItmoBook);

        BookEntity ivanMiptBook = new BookEntity(null, bookMetaEntity, ivan, mipt);
        ivanMiptBook = bookRepository.save(ivanMiptBook);

        BookEntity defuserDefdepBook = new BookEntity(null, bookMetaEntity, defuser, defdep);
        defuserDefdepBook = bookRepository.save(defuserDefdepBook);

        transactionEntries = List.of(
                transactionRepository.save(new TransactionEntity(null, khetagItmoBook, khetag, itmo, null, "", null)),
                transactionRepository.save(new TransactionEntity(null, khetagItmoBook, khetag, itmo, null, "", null)),
                transactionRepository.save(new TransactionEntity(null, khetagItmoBook, khetag, itmo, null, "", null)),
                transactionRepository.save(new TransactionEntity(null, khetagItmoBook, khetag, itmo, null, "", null)),
                transactionRepository.save(new TransactionEntity(null, khetagMiptBook, khetag, mipt, null, "", null)),
                transactionRepository.save(new TransactionEntity(null, khetagMiptBook, khetag, mipt, null, "", null)),
                transactionRepository.save(new TransactionEntity(null, ivanItmoBook, ivan, itmo, null, "", null)),
                transactionRepository.save(new TransactionEntity(null, ivanItmoBook, ivan, itmo, null, "", null)),
                transactionRepository.save(new TransactionEntity(null, ivanItmoBook, ivan, itmo, null, "", null)),
                transactionRepository.save(new TransactionEntity(null, ivanMiptBook, ivan, mipt, null, "", null)),

                transactionRepository.save(new TransactionEntity(null, defuserDefdepBook, defuser, defdep, null, "", null)),
                transactionRepository.save(new TransactionEntity(null, defuserDefdepBook, defuser, defdep, null, "", null)),
                transactionRepository.save(new TransactionEntity(null, defuserDefdepBook, defuser, defdep, null, "", null)),
                transactionRepository.save(new TransactionEntity(null, defuserDefdepBook, defuser, defdep, null, "", null)),
                transactionRepository.save(new TransactionEntity(null, defuserDefdepBook, defuser, defdep, null, "", null)),
                transactionRepository.save(new TransactionEntity(null, defuserDefdepBook, defuser, defdep, null, "", null)));
    }
}
