package com.tinkoff.web.blackbooks.server.domain.dao.respository.impl;

import com.tinkoff.web.blackbooks.server.domain.dao.entry.TransactionEntry;
import com.tinkoff.web.blackbooks.server.domain.dao.respository.TransactionRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionRepositoryImpl extends DefaultRepositoryImpl<TransactionEntry> implements TransactionRepository {
}