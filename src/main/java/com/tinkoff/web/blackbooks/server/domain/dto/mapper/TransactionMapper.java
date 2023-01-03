package com.tinkoff.web.blackbooks.server.domain.dto.mapper;

import com.tinkoff.web.blackbooks.server.dao.entity.TransactionEntity;
import com.tinkoff.web.blackbooks.server.domain.dto.TransactionDto;

public class TransactionMapper implements DtoMapper<TransactionEntity, TransactionDto> {

    public static TransactionMapper INSTANCE = new TransactionMapper();

    @Override
    public TransactionDto toDto(TransactionEntity entry) {
        return null;
    }

    @Override
    public TransactionEntity toEntry(TransactionDto dto) {
        return null;
    }

//    TransactionDto toDto(TransactionEntity car);
//
//    TransactionEntity toEntry(TransactionDto car);

//    private final UserProfileService userProfileService;
//    private final DepositoryService depositoryService;
//
//    public TransactionDto toDto(TransactionEntity transactionEntry) {
//        return null; // todo
////        return new TransactionDto(transactionEntry.getUser().getNick(),
////                transactionEntry.getDepository().getAddress(),
////                transactionEntry.getTime().getTime(),
////                transactionEntry.getAction());
//    }
//
//    @SneakyThrows
//    public TransactionEntity toEntry(TransactionDto transactionDto) {
//        return null; // todo
//
////        return new TransactionEntity(userProfileService.findByNick(transactionDto.nick()).get(),
////                depositoryService.findByAddress(transactionDto.shelf()).get(),
////                new Date(transactionDto.time()),
////                transactionDto.action());
//    }
}
