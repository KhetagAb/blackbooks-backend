package com.tinkoff.web.blackbooks.server.domain.dao.respository.impl;

import com.tinkoff.web.blackbooks.server.domain.dao.entry.Entry;
import com.tinkoff.web.blackbooks.server.domain.dao.respository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class DefaultRepositoryImpl<E extends Entry> implements Repository<E> {

    private final List<E> storage = new ArrayList<>();

    /**
     * Save entry in local storage.
     *
     * @param entry entry to save
     * @return entry with id
     */
    @Override
    public E save(E entry) {
        entry.setId(UUID.randomUUID());
        storage.add(entry);
        return entry;
    }

    /**
     * Get entry by id.
     *
     * @param id id to find entry
     * @return {@link Optional} of entry
     */
    @Override
    public Optional<E> get(UUID id) {
        return storage.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    /**
     * Get all entries
     *
     * @return iterable of entries
     */
    @Override
    public List<E> getAll() {
        return storage;
    }

    /**
     * Update the entry with newEntry by id. If id isn't presented, nothing happens.
     *
     * @param id       old entry's id
     * @param newEntry entry to replace with
     */
    @Override
    public void update(UUID id, E newEntry) {
        storage.replaceAll(e -> {
            if (e.getId() == id) {
                return newEntry;
            } else {
                return e;
            }
        });
    }

    /**
     * Delete entry with id
     *
     * @param id entry's id
     */
    @Override
    public void delete(UUID id) {
        storage.removeIf(e -> e.getId() == id);
    }
}
