package com.tinkoff.web.blackbooks.server.domain.dto.mapper;

import com.tinkoff.web.blackbooks.server.dao.entity.TransactionEntity;
import com.tinkoff.web.blackbooks.server.domain.dto.TransactionDto;

public class TransactionMapper implements DtoMapper<TransactionEntity, TransactionDto> {

    public static TransactionMapper INSTANCE = new TransactionMapper();

    @Override
    public TransactionDto toDto(TransactionEntity entry) {
        return new TransactionDto(entry.getUser().getNick(),
                entry.getDepository().getAddress(), // toDo - address?
                entry.getTimestamp() == null ? 0 : entry.getTimestamp().toInstant().toEpochMilli(), // toDo fix
                entry.getType());
    }

    @Override
    public TransactionEntity toEntry(TransactionDto dto) { // toDO - implement
        return null;
    }
}
