package com.istech.service;

import com.istech.accounts.dto.AccountDto;
import com.istech.accounts.dto.AccountMapper;
import com.istech.accounts.dto.InputAccountDto;
import com.istech.accounts.models.Account;
import com.istech.operations.dto.InputOperationDto;
import com.istech.operations.dto.OperationDto;
import com.istech.operations.dto.OperationMapper;
import com.istech.operations.models.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MapperService {
    private final AccountMapper accountMapper;
    private final OperationMapper operationMapper;

    public Account toAccount(InputAccountDto accountDto) {
        if (accountDto == null) {
            return null;
        }

        return accountMapper.toAccount(accountDto);
    }

    public AccountDto toAccountDto(Account account) {
        if (account == null) {
            return null;
        }

        return accountMapper.toAccountDto(account);
    }

    public Operation toOperation(InputOperationDto operationDto) {
        if (operationDto == null) {
            return null;
        }

        return operationMapper.toOperation(operationDto);
    }

    public OperationDto toOperationDto(Operation operation) {
        if (operation == null) {
            return null;
        }

        return operationMapper.toOperationDto(
                operation,
                toAccountDto(operation.getFromAccount()),
                toAccountDto(operation.getToAccount())
        );
    }
}
