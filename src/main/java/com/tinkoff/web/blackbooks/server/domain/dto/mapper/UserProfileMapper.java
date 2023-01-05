package com.tinkoff.web.blackbooks.server.domain.dto.mapper;

import com.tinkoff.web.blackbooks.server.dao.entity.UserProfileEntity;
import com.tinkoff.web.blackbooks.server.domain.dto.UserProfileDto;

import java.util.Set;

public class UserProfileMapper implements DtoMapper<UserProfileEntity, UserProfileDto> {

    public static UserProfileMapper INSTANCE = new UserProfileMapper();

    @Override
    public UserProfileDto toDto(UserProfileEntity entry) {
        return new UserProfileDto(entry.getNick(),
                entry.getName(),
                String.valueOf(entry.getAge()),
                entry.getGender(),
                entry.getLatitude() + " " + entry.getLongitude());
    }

    @Override
    public UserProfileEntity toEntry(UserProfileDto dto) {
        String[] locations = dto.location().split(" ");
        return new UserProfileEntity(null,
                dto.nick(),
                dto.name(),
                Integer.parseInt(dto.age()),
                dto.gender(),
                Integer.parseInt(locations[0]),
                Integer.parseInt(locations[1]),
                Set.of(),
                Set.of());
    }
}
