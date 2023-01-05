package com.tinkoff.web.blackbooks.server.service;

import com.tinkoff.web.blackbooks.server.dao.entity.DepositoryEntity;
import com.tinkoff.web.blackbooks.server.dao.respository.DepositoryRepository;
import com.tinkoff.web.blackbooks.server.domain.dto.DepositoryDto;
import com.tinkoff.web.blackbooks.server.domain.dto.mapper.DepositoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositoryService extends AbstractService<DepositoryEntity, DepositoryDto> {

    @Autowired
    private DepositoryService(DepositoryRepository repository) {
        super(repository, DepositoryMapper.INSTANCE);
    }
}
