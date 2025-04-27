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

    @PostMapping("/{fromAccountId}/operations")
    @Operation(summary = "Добавление новой операции")
    public OperationDto addOperation(
            @PathVariable UUID fromAccountId,
            @RequestBody @Valid InputOperationDto operationDto) {
        return operationService.addOperation(fromAccountId, operationDto);
    }

    @GetMapping("/{fromAccountId}/operations/{operationId}")
    @Operation(summary = "Получение операции по ID счёта и ID операции")
    public OperationDto getOperationByAccountIdAndOperationId(
            @PathVariable UUID fromAccountId,
            @PathVariable UUID operationId) {
        return operationService.getOperationByAccountIdAndOperationId(fromAccountId, operationId);
    }

    @GetMapping("/{fromAccountId}/operations")
    @Operation(summary = "Получение списка операций по счёту")
    public ListResponse<OperationDto> searchOperations(
            @PathVariable UUID fromAccountId,
            @RequestParam(required = false, defaultValue = "0") Long offset,
            @RequestParam(required = false, defaultValue = "20") Integer limit
    ) {
        return operationService.searchOperations(fromAccountId, offset, limit);
    }

    @DeleteMapping("/{operationId}")
    @Operation(summary = "Удаление операции по ID")
    public void deleteOperationById(@PathVariable UUID operationId) {
        operationService.deleteOperationById(operationId);
    }
}
