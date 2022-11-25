package com.tinkoff.web.blackbooks.server.service;

import com.tinkoff.web.blackbooks.server.domain.dao.dto.UserProfileDto;
import com.tinkoff.web.blackbooks.server.domain.dao.dto.mapper.UserProfileDtoMapper;
import com.tinkoff.web.blackbooks.server.domain.dao.entry.UserProfileEntry;
import com.tinkoff.web.blackbooks.server.domain.dao.respository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService extends AbstractService<UserProfileEntry, UserProfileDto> {

    private final UserProfileRepository userProfileRepository;

    @Autowired
    private UserProfileService(UserProfileRepository repository, UserProfileDtoMapper mapper) {
        super(repository, mapper);
        this.userProfileRepository = repository;
    }

    public Optional<UserProfileEntry> findByNick(String nick) {
        return userProfileRepository.getAll().stream().filter(e -> e.getNick().equals(nick)).findAny();
    }
}
