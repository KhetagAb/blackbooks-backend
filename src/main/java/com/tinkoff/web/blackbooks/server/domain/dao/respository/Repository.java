package com.tinkoff.web.blackbooks.server.domain.dao.respository;

import com.tinkoff.web.blackbooks.server.domain.dao.entry.Entry;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Repository<E extends Entry> {

    E save(E entry);

    Optional<E> get(UUID id);

    List<E> getAll();

    void update(UUID id, E entry);

    void delete(UUID id);
}
