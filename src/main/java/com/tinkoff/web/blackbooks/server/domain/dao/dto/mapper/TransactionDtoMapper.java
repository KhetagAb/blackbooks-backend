package com.tinkoff.web.blackbooks.server.domain.dao.dto.mapper;

import com.tinkoff.web.blackbooks.server.domain.dao.dto.TransactionDto;
import com.tinkoff.web.blackbooks.server.domain.dao.entry.TransactionEntry;
import com.tinkoff.web.blackbooks.server.service.DepositoryService;
import com.tinkoff.web.blackbooks.server.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class TransactionDtoMapper implements DtoMapper<TransactionEntry, TransactionDto> {

    private final UserProfileService userProfileService;
    private final DepositoryService depositoryService;

    public TransactionDto entryToDto(TransactionEntry transactionEntry) {
        return new TransactionDto(transactionEntry.getUser().getNick(),
                transactionEntry.getDepository().getAddress(),
                transactionEntry.getTime().getTime(),
                transactionEntry.getAction());
    }

    @SneakyThrows
    public TransactionEntry dtoToEntry(TransactionDto transactionDto) {
        return new TransactionEntry(userProfileService.findByNick(transactionDto.nick()).get(),
                depositoryService.findByAddress(transactionDto.shelf()).get(),
                new Date(transactionDto.time()),
                transactionDto.action());
    }
}
