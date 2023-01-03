package com.tinkoff.web.blackbooks.server.domain.dto.mapper;

import com.tinkoff.web.blackbooks.server.dao.entity.DepositoryEntity;
import com.tinkoff.web.blackbooks.server.domain.dto.DepositoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepositoryMapper extends DtoMapper<DepositoryEntity, DepositoryDto> {

    DepositoryMapper INSTANCE = Mappers.getMapper(DepositoryMapper.class);

//    DepositoryDto toDto(DepositoryEntity car);
//
//    DepositoryEntity toEntry(DepositoryDto car);
}