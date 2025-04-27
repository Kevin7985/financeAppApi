package com.istech.operations.dto;

import com.istech.operations.models.OperationType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputOperationDto {
    @NotNull(message = "Тип операции не может быть пустым")
    @NotNull(message = "Тип операции не может быть пустым")
    private OperationType operationType;

    private UUID toAccountId;

    private String description;

    @Min(value = 1, message = "Сумма операции не может быть меньше 1")
    private Long amount;
}
