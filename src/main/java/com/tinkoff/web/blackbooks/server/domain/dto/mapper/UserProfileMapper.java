package com.tinkoff.web.blackbooks.server.domain.dto.mapper;

import com.tinkoff.web.blackbooks.server.dao.entity.UserProfileEntity;
import com.tinkoff.web.blackbooks.server.domain.dto.UserProfileDto;

public class UserProfileMapper implements DtoMapper<UserProfileEntity, UserProfileDto> {

    public static UserProfileMapper INSTANCE = new UserProfileMapper();

    @Override
    public UserProfileDto toDto(UserProfileEntity entry) {
        return null;
    }

    @Override
    public UserProfileEntity toEntry(UserProfileDto dto) {
        return null;
    }


//    public UserProfileDto toDto(UserProfileEntity userProfileEntry) {
//        return new UserProfileDto(userProfileEntry.getNick(),
//                userProfileEntry.getName(),
//                String.valueOf(userProfileEntry.getAge()),
//                userProfileEntry.getGender(),
//                userProfileEntry.getLatitude() + " " + userProfileEntry.getLongitude());
//    }
//
//    public UserProfileEntity toEntry(UserProfileDto userProfileDto) {
//        return null; // todo
//
////        return new UserProfileEntity(userProfileDto.nick(),
////                userProfileDto.name(),
////                Integer.parseInt(userProfileDto.age()),
////                userProfileDto.gender(),
////                userProfileDto.location());
//    }
}
