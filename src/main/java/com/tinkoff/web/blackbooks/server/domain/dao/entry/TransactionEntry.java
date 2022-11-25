package com.tinkoff.web.blackbooks.server.domain.dao.entry;

import lombok.Getter;

import java.util.Date;

@Getter
public class TransactionEntry extends BaseEntry {

    private final UserProfileEntry user;
    private final DepositoryEntry depository;
    private final Date time;
    private final String action;

    public TransactionEntry(UserProfileEntry user, DepositoryEntry depository, Date time, String action) {
        this.user = user;
        this.depository = depository;
        this.time = time;
        this.action = action;
    }
}
