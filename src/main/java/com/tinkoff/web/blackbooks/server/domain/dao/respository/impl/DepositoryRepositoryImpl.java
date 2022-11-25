package com.tinkoff.web.blackbooks.server.domain.dao.respository.impl;

import com.tinkoff.web.blackbooks.server.domain.dao.entry.DepositoryEntry;
import com.tinkoff.web.blackbooks.server.domain.dao.respository.DepositoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DepositoryRepositoryImpl extends DefaultRepositoryImpl<DepositoryEntry> implements DepositoryRepository {
}
