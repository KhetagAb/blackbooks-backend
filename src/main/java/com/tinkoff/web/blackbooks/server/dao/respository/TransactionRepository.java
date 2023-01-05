package com.tinkoff.web.blackbooks.server.dao.respository;

import com.tinkoff.web.blackbooks.server.dao.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {
}
