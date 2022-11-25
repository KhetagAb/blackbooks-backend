package com.tinkoff.web.blackbooks.server.domain.dao.dto.mapper;

import com.tinkoff.web.blackbooks.server.domain.dao.dto.DepositoryDto;
import com.tinkoff.web.blackbooks.server.domain.dao.entry.DepositoryEntry;
import org.springframework.stereotype.Component;

@Component
public class DepositoryDtoMapper implements DtoMapper<DepositoryEntry, DepositoryDto> {

    public DepositoryDto entryToDto(DepositoryEntry depositoryEntry) {
        return new DepositoryDto(depositoryEntry.getNick(),
                depositoryEntry.getAddress(),
                depositoryEntry.getDescription(),
                depositoryEntry.getType(),
                depositoryEntry.getLocation());
    }

    public DepositoryEntry dtoToEntry(DepositoryDto depositoryDto) {
        return new DepositoryEntry(depositoryDto.nick(),
                depositoryDto.address(),
                depositoryDto.description(),
                depositoryDto.type(),
                depositoryDto.location());
    }
}
