package com.tinkoff.web.blackbooks.server.domain.dto.mapper;

import com.tinkoff.web.blackbooks.server.dao.entity.TransactionEntity;
import com.tinkoff.web.blackbooks.server.domain.dto.TransactionDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper extends DtoMapper<TransactionEntity, TransactionDto> {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

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
