package com.istech.operations;

import com.istech.operations.dto.InputOperationDto;
import com.istech.operations.dto.OperationDto;
import com.istech.utils.models.ListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
@Tag(name = "Operations", description = "Методы для работы с операциями над счетами")
@CrossOrigin("*")
@Validated
public class OperationController {
    private final OperationService operationService;

    @PostMapping("/{accountId}/operations")
    @Operation(summary = "Добавление новой операции")
    public OperationDto addOperation(
            @PathVariable UUID accountId,
            @RequestBody @Valid InputOperationDto operationDto) {
        return operationService.addOperation(accountId, operationDto);
    }

    @GetMapping("/{accountId}/operations/{operationId}")
    @Operation(summary = "Получение операции по ID счёта и ID операции")
    public OperationDto getOperationByAccountIdAndOperationId(
            @PathVariable UUID accountId,
            @PathVariable UUID operationId) {
        return operationService.getOperationByAccountIdAndOperationId(accountId, operationId);
    }

    @GetMapping("/{accountId}/operations")
    @Operation(summary = "Получение списка операций по счёту")
    public ListResponse<OperationDto> searchOperations(
            @PathVariable UUID accountId,
            @RequestParam(required = false, defaultValue = "0") Long offset,
            @RequestParam(required = false, defaultValue = "20") Integer limit
    ) {
        return operationService.searchOperations(accountId, offset, limit);
    }
}
