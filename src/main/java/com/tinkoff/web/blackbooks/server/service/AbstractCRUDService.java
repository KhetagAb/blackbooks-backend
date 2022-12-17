package com.tinkoff.web.blackbooks.server.service;


import com.tinkoff.web.blackbooks.server.domain.dao.dto.mapper.DtoMapper;
import com.tinkoff.web.blackbooks.server.domain.dao.entry.Entry;
import com.tinkoff.web.blackbooks.server.domain.dao.respository.Repository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequiredArgsConstructor
public abstract class AbstractCRUDService<E extends Entry, D> {

    private final Repository<E> repository;
    private final DtoMapper<E, D> mapper;

    public UUID create(D dto) {
        return repository.save(mapper.dtoToEntry(dto)).getId();
    }

    public Mono<D> get(UUID id) {
        return Mono.justOrEmpty(mapper.entryToDto(repository.get(id)));
    }

    public Flux<D> getAll() {
        return Flux.fromIterable(mapper.entriesToDto(repository.getAll()));
    }

    public void update(UUID id, D dto) {
        repository.update(id, mapper.dtoToEntry(dto));
    }

    public void delete(UUID id) {
        repository.delete(id);
    }
}
