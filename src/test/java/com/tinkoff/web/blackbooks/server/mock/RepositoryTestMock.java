package com.tinkoff.web.blackbooks.server.mock;

import com.tinkoff.web.blackbooks.server.dao.entity.DepositoryEntity;
import com.tinkoff.web.blackbooks.server.dao.entity.TransactionEntity;
import com.tinkoff.web.blackbooks.server.dao.entity.UserProfileEntity;
import com.tinkoff.web.blackbooks.server.dao.respository.DepositoryRepository;
import com.tinkoff.web.blackbooks.server.dao.respository.TransactionRepository;
import com.tinkoff.web.blackbooks.server.dao.respository.UserProfileRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Getter
public class RepositoryTestMock {

    private final List<UserProfileEntity> userProfileEntries;

    private final List<DepositoryEntity> depositoryEntries;

    private final List<TransactionEntity> transactionEntries;

    // toDo: refactor with true MOCKITO
    private RepositoryTestMock(@Autowired UserProfileRepository userProfileRepository,
                               @Autowired DepositoryRepository depositoryRepository,
                               @Autowired TransactionRepository transactionRepository) {
        UserProfileEntity khetag = userProfileRepository.save(new UserProfileEntity(null, "KhetagDz", "Khetag", 20, "male", 0, 0));
        UserProfileEntity ivan = userProfileRepository.save(new UserProfileEntity(null, "IvanM3239", "Ivan", 19, "male", 0, 2));
        UserProfileEntity defuser = userProfileRepository.save(new UserProfileEntity(null, "string", "string", 0, "string", 0, 2));

        userProfileEntries = List.of(khetag, ivan, defuser);

        DepositoryEntity itmo = depositoryRepository.save(new DepositoryEntity(null, "ITMO library",
                "St. Petersburg",
                "enter in the front of the building",
                "bookshelf",
                0,
                0));

        DepositoryEntity mipt = depositoryRepository.save(new DepositoryEntity(null, "MIPT library",
                "Moscow",
                "enter in the front of the building",
                "bookshelf",
                0,
                0));

        DepositoryEntity defdep = depositoryRepository.save(new DepositoryEntity(null, "string",
                "string",
                "string",
                "string",
                0,
                0));

        depositoryEntries = List.of(itmo, mipt, defdep);

        transactionEntries = List.of(
                transactionRepository.save(new TransactionEntity(null, khetag.getId(), itmo.getId(), UUID.randomUUID(), null, "rent", null)),
                transactionRepository.save(new TransactionEntity(null, khetag.getId(), itmo.getId(), UUID.randomUUID(), null, "rent", null)),
                transactionRepository.save(new TransactionEntity(null, khetag.getId(), itmo.getId(), UUID.randomUUID(), null, "rent", null)),
                transactionRepository.save(new TransactionEntity(null, khetag.getId(), itmo.getId(), UUID.randomUUID(), null, "returnal", null)),
                transactionRepository.save(new TransactionEntity(null, khetag.getId(), mipt.getId(), UUID.randomUUID(), null, "rent", null)),
                transactionRepository.save(new TransactionEntity(null, khetag.getId(), mipt.getId(), UUID.randomUUID(), null, "returnal", null)),
                transactionRepository.save(new TransactionEntity(null, ivan.getId(), itmo.getId(), UUID.randomUUID(), null, "rent", null)),
                transactionRepository.save(new TransactionEntity(null, ivan.getId(), itmo.getId(), UUID.randomUUID(), null, "rent", null)),
                transactionRepository.save(new TransactionEntity(null, ivan.getId(), itmo.getId(), UUID.randomUUID(), null, "rent", null)),
                transactionRepository.save(new TransactionEntity(null, ivan.getId(), mipt.getId(), UUID.randomUUID(), null, "rent", null)),

                transactionRepository.save(new TransactionEntity(null, defuser.getId(), defdep.getId(), UUID.randomUUID(), null, "1", null)),
                transactionRepository.save(new TransactionEntity(null, defuser.getId(), defdep.getId(), UUID.randomUUID(), null, "2", null)),
                transactionRepository.save(new TransactionEntity(null, defuser.getId(), defdep.getId(), UUID.randomUUID(), null, "3", null)),
                transactionRepository.save(new TransactionEntity(null, defuser.getId(), defdep.getId(), UUID.randomUUID(), null, "4", null)),
                transactionRepository.save(new TransactionEntity(null, defuser.getId(), defdep.getId(), UUID.randomUUID(), null, "5", null)),
                transactionRepository.save(new TransactionEntity(null, defuser.getId(), defdep.getId(), UUID.randomUUID(), null, "6", null)));
    }
}
