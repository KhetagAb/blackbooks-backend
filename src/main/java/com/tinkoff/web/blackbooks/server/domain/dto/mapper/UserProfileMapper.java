package com.tinkoff.web.blackbooks.server.domain.dto.mapper;

import com.tinkoff.web.blackbooks.server.dao.entity.UserProfileEntity;
import com.tinkoff.web.blackbooks.server.domain.dto.UserProfileDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserProfileMapper extends DtoMapper<UserProfileEntity, UserProfileDto> {

    UserProfileMapper INSTANCE = Mappers.getMapper(UserProfileMapper.class);


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
