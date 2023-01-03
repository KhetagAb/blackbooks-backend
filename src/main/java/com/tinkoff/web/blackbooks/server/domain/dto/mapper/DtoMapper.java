package com.tinkoff.web.blackbooks.server.domain.dto.mapper;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public interface DtoMapper<E, D> {

    D toDto(E entry);

    E toEntry(D dto);

    default Optional<D> toDto(Optional<E> entry) {
        return entry.map(this::toDto);
    }

    default Collection<D> toDtos(Iterable<E> entries) {
        return StreamSupport.stream(entries.spliterator(), false).map(this::toDto).collect(Collectors.toList());
    }
}
