package com.istech.operations;

import com.istech.operations.dto.InputOperationDto;
import com.istech.operations.dto.OperationDto;
import com.istech.utils.models.ListResponse;

import java.util.UUID;

public interface OperationService {
    OperationDto addOperation(UUID accountId, InputOperationDto operationDto);
    OperationDto getOperationByAccountIdAndOperationId(UUID accountId, UUID operationId);
    ListResponse<OperationDto> searchOperations(UUID accountId, Long offset, Integer limit);
}
