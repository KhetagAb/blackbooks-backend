package com.tinkoff.web.blackbooks.server.domain.dto.mapper;

import com.tinkoff.web.blackbooks.server.dao.entity.DepositoryEntity;
import com.tinkoff.web.blackbooks.server.domain.dto.DepositoryDto;

import java.util.Set;

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
        String[] locations = dto.location().split(" "); // toDo refactor on utils
        return new DepositoryEntity(null, dto.nick(),
                dto.address(),
                dto.description(),
                dto.type(),
                Integer.parseInt(locations[0]),
                Integer.parseInt(locations[1]),
                Set.of(),
                Set.of());
    }

//    DepositoryDto toDto(DepositoryEntity car);
//
//    DepositoryEntity toEntry(DepositoryDto car);
}