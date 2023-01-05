package com.tinkoff.web.blackbooks.server.service;

import com.tinkoff.web.blackbooks.server.controller.util.SortType;
import com.tinkoff.web.blackbooks.server.dao.entity.TransactionEntity;
import com.tinkoff.web.blackbooks.server.dao.respository.TransactionRepository;
import com.tinkoff.web.blackbooks.server.domain.dto.TransactionDto;
import com.tinkoff.web.blackbooks.server.domain.dto.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Comparator;
import java.util.UUID;
import java.util.function.Predicate;

@Service
public class TransactionsService extends AbstractService<TransactionEntity, TransactionDto> {

    @Autowired
    private TransactionsService(TransactionRepository repository) {
        super(repository, TransactionMapper.INSTANCE);
    }

    public Flux<TransactionDto> getTransactions(UUID bookDepositId, UUID bookHunterId, long amount, SortType type) {
        Predicate<TransactionEntity> filter = t -> t.getUser().getId().equals(bookHunterId) && t.getDepository().getId().equals(bookDepositId);
        Comparator<TransactionEntity> comparator = Comparator.comparing(TransactionEntity::getTimestamp,
                type.equals(SortType.ASC) ? Comparator.naturalOrder() : Comparator.reverseOrder());

        return Flux.fromStream(repository.findAll().stream()
                        .filter(filter)
                        .sorted(comparator)
                        .limit(amount))
                .map(mapper::toDto);
    }
}
