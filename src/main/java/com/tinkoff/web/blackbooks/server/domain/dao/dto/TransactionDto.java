package com.tinkoff.web.blackbooks.server.domain.dao.dto;

import java.util.Date;

public record TransactionDto(String nick, String shelf, Date time, String action) {
}
