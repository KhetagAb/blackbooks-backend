package com.tinkoff.web.blackbooks.server.domain.dao.entry;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class BaseEntry implements Entry {

    @Getter
    @Setter
    private UUID id;
}
