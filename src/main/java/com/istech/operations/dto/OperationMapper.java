package com.istech.operations.dto;

import com.istech.accounts.dto.AccountDto;
import com.istech.accounts.models.Account;
import com.istech.operations.models.Operation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OperationMapper {
    public Operation toOperation(InputOperationDto operationDto) {
        return new Operation(
                null,
                null,
                null,
                operationDto.getOperationType(),
                operationDto.getDescription(),
                operationDto.getAmount(),
                LocalDateTime.now()
        );
    }

    public OperationDto toOperationDto(Operation operation, AccountDto fromAccountDto, AccountDto toAccountDto) {
        return new OperationDto(
                operation.getId(),
                fromAccountDto,
                toAccountDto,
                operation.getOperationType(),
                operation.getDescription(),
                operation.getAmount(),
                operation.getCreatedAt()
        );
    }
}
