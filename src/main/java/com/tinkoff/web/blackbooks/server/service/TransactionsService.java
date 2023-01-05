package com.tinkoff.web.blackbooks.server.service;

import com.tinkoff.web.blackbooks.server.controller.util.SortType;
import com.tinkoff.web.blackbooks.server.domain.dao.dto.TransactionDto;
import com.tinkoff.web.blackbooks.server.domain.dao.dto.mapper.TransactionDtoMapper;
import com.tinkoff.web.blackbooks.server.domain.dao.entry.TransactionEntry;
import com.tinkoff.web.blackbooks.server.domain.dao.respository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Comparator;
import java.util.UUID;
import java.util.function.Predicate;

@Service
public class TransactionsService extends AbstractService<TransactionEntry, TransactionDto> {

    private final TransactionRepository transactionRepository;
    private final TransactionDtoMapper transactionDtoMapper;

    @Autowired
    private TransactionsService(TransactionRepository repository, TransactionDtoMapper mapper) {
        super(repository, mapper);
        this.transactionRepository = repository;
        this.transactionDtoMapper = mapper;
    }

    public Flux<TransactionDto> getTransactions(UUID bookDepositId, UUID bookHunterId, Long amount, SortType type) {
        Predicate<TransactionEntry> filter = t -> t.getUser().getId().equals(bookHunterId) && t.getDepository().getId().equals(bookDepositId);
        Comparator<TransactionEntry> comparator = Comparator.comparing(TransactionEntry::getTime,
                type.equals(SortType.ASC) ? Comparator.naturalOrder() : Comparator.reverseOrder());

        // toDo:: synch on the moment :( emit on dto ready?
        return Flux.fromStream(transactionRepository.getAll().stream()
                        .filter(filter)
                        .sorted(comparator)
                        .limit(amount))
                .map(transactionDtoMapper::entryToDto);
    }
}
