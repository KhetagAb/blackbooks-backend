package com.tinkoff.web.blackbooks.server.service;


import com.tinkoff.web.blackbooks.server.dao.entity.Entity;
import com.tinkoff.web.blackbooks.server.domain.dto.mapper.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequiredArgsConstructor
public abstract class AbstractService<E extends Entity, D> {

    protected final JpaRepository<E, UUID> repository;
    protected final DtoMapper<E, D> mapper;

    public UUID create(D dto) {
        return repository.save(mapper.toEntry(dto)).getId();
    }

    public Mono<D> get(UUID id) {
        return Mono.justOrEmpty(mapper.toDto(repository.findById(id)));
    }

    public Flux<D> getAll() {
        return Flux.fromIterable(mapper.toDtos(repository.findAll()));
    }

    public void update(UUID id, D dto) {
        E entry = mapper.toEntry(dto);
        entry.setId(id);
        repository.save(entry);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
