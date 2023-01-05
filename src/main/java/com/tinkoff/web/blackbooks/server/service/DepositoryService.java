package com.tinkoff.web.blackbooks.server.service;

import com.tinkoff.web.blackbooks.server.domain.dao.dto.DepositoryDto;
import com.tinkoff.web.blackbooks.server.domain.dao.dto.mapper.DepositoryDtoMapper;
import com.tinkoff.web.blackbooks.server.domain.dao.entry.DepositoryEntry;
import com.tinkoff.web.blackbooks.server.domain.dao.respository.DepositoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepositoryService extends AbstractService<DepositoryEntry, DepositoryDto> {

    private final DepositoryRepository depositoryRepository;

    @Autowired
    private DepositoryService(DepositoryRepository repository, DepositoryDtoMapper mapper) {
        super(repository, mapper);
        this.depositoryRepository = repository;
    }

    public Optional<DepositoryEntry> findByAddress(String shelf) {
        return depositoryRepository.getAll().stream()
                .filter(e -> e.getAddress().equals(shelf))
                .findAny();
    }
}
