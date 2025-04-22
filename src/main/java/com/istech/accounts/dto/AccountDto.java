package com.istech.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class AccountDto {
    private final UUID id;
    private final String title;
    private final Long balance;
    private final LocalDateTime createdAt;
}
