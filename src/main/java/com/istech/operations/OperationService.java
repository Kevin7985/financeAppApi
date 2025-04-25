package com.istech.operations;

import com.istech.operations.dto.InputOperationDto;
import com.istech.operations.dto.OperationDto;

import java.util.UUID;

public interface OperationService {
    OperationDto addOperation(UUID accountId, InputOperationDto operationDto);
    OperationDto getOperationByAccountIdAndOperationId(UUID accountId, UUID operationId);
}
