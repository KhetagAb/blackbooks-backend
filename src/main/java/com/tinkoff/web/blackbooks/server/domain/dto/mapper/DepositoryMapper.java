package com.tinkoff.web.blackbooks.server.domain.dto.mapper;

import com.tinkoff.web.blackbooks.server.dao.entity.DepositoryEntity;
import com.tinkoff.web.blackbooks.server.domain.dto.DepositoryDto;

public class DepositoryMapper implements DtoMapper<DepositoryEntity, DepositoryDto> {

    public static DepositoryMapper INSTANCE = new DepositoryMapper();

    @Override
    public DepositoryDto toDto(DepositoryEntity entry) {
        return new DepositoryDto(entry.getName(),
                entry.getAddress(),
                entry.getDescription(),
                entry.getType(),
                entry.getLatitude() + " " + entry.getLongitude());
    }

    @Override
    public DepositoryEntity toEntry(DepositoryDto dto) {
        return new DepositoryEntity(null, dto.nick(),
                dto.address(),
                dto.description(),
                dto.type(),
                0,
                0);
    }

//    DepositoryDto toDto(DepositoryEntity car);
//
//    DepositoryEntity toEntry(DepositoryDto car);
}