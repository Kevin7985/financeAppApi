package com.istech.operations;

import com.istech.accounts.AccountRepository;
import com.istech.accounts.exceptions.AccountNotFoundException;
import com.istech.accounts.models.Account;
import com.istech.operations.dto.InputOperationDto;
import com.istech.operations.dto.OperationDto;
import com.istech.operations.exceptions.OperationValidationException;
import com.istech.operations.models.Operation;
import com.istech.operations.models.OperationType;
import com.istech.service.MapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService {
    private final MapperService mapperService;
    private final OperationRepository operationRepository;

    private final AccountRepository accountRepository;

    @Override
    public OperationDto addOperation(UUID accountId, InputOperationDto operationDto) {
        Account a = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Счёт с данным ID не найден"));

        Operation o = mapperService.toOperation(operationDto);

        if (o.getOperationType().equals(OperationType.OUTCOME)) {
            if (a.getBalance() < o.getAmount()) {
                throw new OperationValidationException("Сумма операции не может быть больше той, которая есть на балансе");
            }

            a.setBalance(a.getBalance() - o.getAmount());
        } else {
            a.setBalance(a.getBalance() + o.getAmount());
        }
        a = accountRepository.save(a);

        o.setAccount(a);
        o = operationRepository.save(o);
        return mapperService.toOperationDto(o);
    }

    @Override
    public OperationDto getOperationByAccountIdAndOperationId(UUID accountId, UUID operationId) {
        return null;
    }
}
