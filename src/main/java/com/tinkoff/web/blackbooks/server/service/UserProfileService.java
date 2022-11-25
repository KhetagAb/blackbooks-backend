package com.tinkoff.web.blackbooks.server.service;

import com.tinkoff.web.blackbooks.server.domain.dao.dto.UserProfileDto;
import com.tinkoff.web.blackbooks.server.domain.dao.dto.mapper.UserProfileDtoMapper;
import com.tinkoff.web.blackbooks.server.domain.dao.entry.UserProfileEntry;
import com.tinkoff.web.blackbooks.server.domain.dao.respository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserProfileService extends AbstractService<UserProfileEntry, UserProfileDto> {

    private final UserProfileRepository userProfileRepository;
    private final UserProfileDtoMapper userProfileDtoMapper;

    @Autowired
    private UserProfileService(UserProfileRepository repository, UserProfileDtoMapper mapper) {
        super(repository, mapper);
        this.userProfileRepository = repository;
        this.userProfileDtoMapper = mapper;
    }

    public UUID create(UserProfileDto userProfileDto) {
        return userProfileRepository.save(userProfileDtoMapper.dtoToEntry(userProfileDto)).getId();
    }

    public Mono<UserProfileDto> get(UUID id) {
        return Mono.justOrEmpty(userProfileDtoMapper.entryToDto(userProfileRepository.get(id)));
    }

    public Flux<UserProfileDto> getAll() {
        return Flux.fromIterable(userProfileDtoMapper.entriesToDto(userProfileRepository.getAll()));
    }

    public void update(UUID id, UserProfileDto userProfileDto) {
        userProfileRepository.update(id, userProfileDtoMapper.dtoToEntry(userProfileDto));
    }

    public void delete(UUID id) {
        userProfileRepository.delete(id);
    }

    public Optional<UserProfileEntry> findByNick(String nick) {
        return userProfileRepository.getAll().stream().filter(e -> e.getNick().equals(nick)).findAny();
    }
}
