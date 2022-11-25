package com.tinkoff.web.blackbooks.server.domain.dao.entry;

import lombok.Getter;

@Getter
public class DepositoryEntry extends BaseEntry {

    private final String nick;
    private final String address;
    private final String description;
    private final String type;
    private final String location;

    public DepositoryEntry(String nick, String address, String description, String type, String location) {
        this.nick = nick;
        this.address = address;
        this.description = description;
        this.type = type;
        this.location = location;
    }
}
