package com.tinkoff.web.blackbooks.server.domain.dao.respository;

import com.tinkoff.web.blackbooks.server.domain.dao.entry.DepositoryEntry;
import com.tinkoff.web.blackbooks.server.domain.dao.entry.TransactionEntry;
import com.tinkoff.web.blackbooks.server.domain.dao.entry.UserProfileEntry;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Getter
public class RepositoryInitializer {

    private final List<UserProfileEntry> userProfileEntries;
    private final List<DepositoryEntry> depositoryEntries;

    private final List<TransactionEntry> transactionEntries;

    private RepositoryInitializer(@Autowired UserProfileRepository userProfileRepository,
                                  @Autowired DepositoryRepository depositoryRepository,
                                  @Autowired TransactionRepository transactionRepository) {
        UserProfileEntry khetag = userProfileRepository.save(new UserProfileEntry("KhetagDz", "Khetag", 20, "male", "St. Petersburg"));
        UserProfileEntry ivan = userProfileRepository.save(new UserProfileEntry("IvanM3239", "Ivan", 19, "male", "Moscow"));
        UserProfileEntry defuser = userProfileRepository.save(new UserProfileEntry("string", "string", 0, "string", "string"));

        userProfileEntries = List.of(khetag, ivan, defuser);

        DepositoryEntry itmo = depositoryRepository.save(new DepositoryEntry("ITMO library",
                "St. Petersburg",
                "enter in the front of the building",
                "bookshelf",
                "St. Petersubrg"));

        DepositoryEntry mipt = depositoryRepository.save(new DepositoryEntry("MIPT library",
                "Moscow",
                "enter in the front of the building",
                "bookshelf",
                "Moscow"));

        DepositoryEntry defdep = depositoryRepository.save(new DepositoryEntry("string",
                "string",
                "string",
                "string",
                "string"));

        depositoryEntries = List.of(itmo, mipt, defdep);

        transactionEntries = List.of(
                transactionRepository.save(new TransactionEntry(khetag, itmo, new Date(0), "rent")),
                transactionRepository.save(new TransactionEntry(khetag, itmo, new Date(36000000), "rent")),
                transactionRepository.save(new TransactionEntry(khetag, itmo, new Date(36010000), "rent")),
                transactionRepository.save(new TransactionEntry(khetag, itmo, new Date(36100000), "returnal")),
                transactionRepository.save(new TransactionEntry(khetag, mipt, new Date(36500000), "rent")),
                transactionRepository.save(new TransactionEntry(khetag, mipt, new Date(2 * 36000000), "returnal")),
                transactionRepository.save(new TransactionEntry(ivan, itmo, new Date(36000010), "rent")),
                transactionRepository.save(new TransactionEntry(ivan, itmo, new Date(10), "rent")),
                transactionRepository.save(new TransactionEntry(ivan, itmo, new Date(36000010), "rent")),
                transactionRepository.save(new TransactionEntry(ivan, mipt, new Date(10), "rent")),

                transactionRepository.save(new TransactionEntry(defuser, defdep, new Date(0), "1")),
                transactionRepository.save(new TransactionEntry(defuser, defdep, new Date(1), "2")),
                transactionRepository.save(new TransactionEntry(defuser, defdep, new Date(2), "3")),
                transactionRepository.save(new TransactionEntry(defuser, defdep, new Date(3), "4")),
                transactionRepository.save(new TransactionEntry(defuser, defdep, new Date(4), "5")),
                transactionRepository.save(new TransactionEntry(defuser, defdep, new Date(5), "6")));


    }
}
