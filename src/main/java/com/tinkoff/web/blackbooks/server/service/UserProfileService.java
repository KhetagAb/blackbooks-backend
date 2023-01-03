package com.tinkoff.web.blackbooks.server.service;

import com.tinkoff.web.blackbooks.server.dao.entity.UserProfileEntity;
import com.tinkoff.web.blackbooks.server.dao.respository.UserProfileRepository;
import com.tinkoff.web.blackbooks.server.domain.dto.UserProfileDto;
import com.tinkoff.web.blackbooks.server.domain.dto.mapper.UserProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService extends AbstractService<UserProfileEntity, UserProfileDto> {

    private final UserProfileRepository userProfileRepository;

    @Autowired
    private UserProfileService(UserProfileRepository repository) {
        super(repository, UserProfileMapper.INSTANCE);
        this.userProfileRepository = repository;
    }

    public Optional<UserProfileEntity> findByNick(String nick) {
        return userProfileRepository.findAll().stream()
                .filter(e -> e.getNick().equals(nick))
                .findAny(); // toDO optimize by jpa operation
    }
}
