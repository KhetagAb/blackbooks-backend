package com.tinkoff.web.blackbooks.server.dao.respository;

import com.tinkoff.web.blackbooks.server.dao.entity.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfileEntity, UUID> {
}
