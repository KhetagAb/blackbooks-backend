package com.tinkoff.web.blackbooks.server.domain.dao.dto.mapper;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public interface DtoMapper<E, D> {

    D entryToDto(E entry);

    E dtoToEntry(D dto);

    default Optional<D> entryToDto(Optional<E> entry) {
        return entry.map(this::entryToDto);
    }

    default Collection<D> entriesToDto(Collection<E> entries) {
        return entries.stream().map(this::entryToDto).collect(Collectors.toList());
    }
}
