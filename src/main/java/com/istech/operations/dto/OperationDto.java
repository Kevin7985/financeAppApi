package com.istech.operations.dto;

import com.istech.accounts.dto.AccountDto;
import com.istech.operations.models.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class OperationDto {
    private final UUID id;
    private final AccountDto fromAccount;
    private final AccountDto toAccount;
    private final OperationType operationType;
    private final String description;
    private final Long amount;
    private final LocalDateTime createdAt;
}
