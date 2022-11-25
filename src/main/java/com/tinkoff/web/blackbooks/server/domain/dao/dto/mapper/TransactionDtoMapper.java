package com.tinkoff.web.blackbooks.server.domain.dao.dto.mapper;

import com.tinkoff.web.blackbooks.server.domain.dao.dto.TransactionDto;
import com.tinkoff.web.blackbooks.server.domain.dao.entry.TransactionEntry;
import com.tinkoff.web.blackbooks.server.service.DepositoryService;
import com.tinkoff.web.blackbooks.server.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionDtoMapper implements DtoMapper<TransactionEntry, TransactionDto> {

    private final UserProfileService userProfileService;
    private final DepositoryService depositoryService;

    public TransactionDto entryToDto(TransactionEntry transactionEntry) {
        return new TransactionDto(transactionEntry.getUser().getNick(),
                transactionEntry.getDepository().getAddress(),
                transactionEntry.getTime(),
                transactionEntry.getAction());
    }

    public TransactionEntry dtoToEntry(TransactionDto transactionDto) {
        return new TransactionEntry(userProfileService.findByNick(transactionDto.nick()).get(),
                depositoryService.findByAddress(transactionDto.shelf()).get(),
                transactionDto.time(),
                transactionDto.action());
    }
}
