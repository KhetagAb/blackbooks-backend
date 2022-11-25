package com.tinkoff.web.blackbooks.server.domain.dao.respository.impl;

import com.tinkoff.web.blackbooks.server.domain.dao.entry.UserProfileEntry;
import com.tinkoff.web.blackbooks.server.domain.dao.respository.UserProfileRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserProfileRepositoryImpl extends DefaultRepositoryImpl<UserProfileEntry> implements UserProfileRepository {
}
