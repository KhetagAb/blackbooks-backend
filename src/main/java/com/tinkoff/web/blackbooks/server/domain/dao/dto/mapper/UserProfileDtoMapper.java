package com.tinkoff.web.blackbooks.server.domain.dao.dto.mapper;

import com.tinkoff.web.blackbooks.server.domain.dao.dto.UserProfileDto;
import com.tinkoff.web.blackbooks.server.domain.dao.entry.UserProfileEntry;
import org.springframework.stereotype.Component;

@Component
public class UserProfileDtoMapper implements DtoMapper<UserProfileEntry, UserProfileDto> {

    public UserProfileDto entryToDto(UserProfileEntry userProfileEntry) {
        return new UserProfileDto(userProfileEntry.getNick(),
                userProfileEntry.getName(),
                String.valueOf(userProfileEntry.getAge()),
                userProfileEntry.getGender(),
                userProfileEntry.getLocation());
    }

    public UserProfileEntry dtoToEntry(UserProfileDto userProfileDto) {
        return new UserProfileEntry(userProfileDto.nick(),
                userProfileDto.name(),
                Integer.parseInt(userProfileDto.age()),
                userProfileDto.gender(),
                userProfileDto.location());
    }
}
