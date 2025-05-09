package com.istech.operations;

import com.istech.accounts.AccountRepository;
import com.istech.accounts.exceptions.AccountNotFoundException;
import com.istech.accounts.models.Account;
import com.istech.operations.dto.InputOperationDto;
import com.istech.operations.dto.OperationDto;
import com.istech.operations.exceptions.OperationNotFoundException;
import com.istech.operations.exceptions.OperationValidationException;
import com.istech.operations.models.Operation;
import com.istech.operations.models.OperationType;
import com.istech.service.MapperService;
import com.istech.utils.models.ListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

        if (o.getOperationType().equals(OperationType.TRANSFER)) {
            Account b = accountRepository.findById(operationDto.getToAccountId())
                    .orElseThrow(() -> new AccountNotFoundException("Счёт-получатель с данным ID не найден"));

            if (a.getBalance() < o.getAmount()) {
                throw new OperationValidationException("Сумма операции не может быть больше той, которая есть на балансе");
            }

            a.setBalance(a.getBalance() - o.getAmount());
            b.setBalance(b.getBalance() + o.getAmount());

            a = accountRepository.save(a);
            b = accountRepository.save(b);

            o.setFromAccount(a);
            o.setToAccount(b);
        } else {
            if (o.getOperationType().equals(OperationType.OUTCOME)) {
                if (a.getBalance() < o.getAmount()) {
                    throw new OperationValidationException("Сумма операции не может быть больше той, которая есть на балансе");
                }

                a.setBalance(a.getBalance() - o.getAmount());
            } else {
                a.setBalance(a.getBalance() + o.getAmount());
            }
            a = accountRepository.save(a);

            o.setFromAccount(a);
        }

         o = operationRepository.save(o);
        return mapperService.toOperationDto(o);
    }

    @Override
    public ListResponse<OperationDto> searchOperations(UUID accountId, Long offset, Integer limit) {
        Account a = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Счёт с данным ID не найден"));

        Pageable pageable = PageRequest.of((int) (offset / limit), limit, Sort.by("createdAt").descending());

        List<OperationDto> results = operationRepository.findByAccount_IdOrderByCreatedAtDesc(accountId, pageable).stream()
                .map(mapperService::toOperationDto)
                .toList();

        return new ListResponse<>(
                operationRepository.findByAccount_IdCount(accountId),
                results
        );
    }

    @Override
    public void deleteOperationById(UUID operationId) {
        Operation o = operationRepository.findById(operationId)
                .orElseThrow(() -> new OperationNotFoundException("Операция с данным ID не найдена"));

        Account a = o.getFromAccount();

        if (o.getOperationType().equals(OperationType.TRANSFER)) {
            Account b = o.getToAccount();

            a.setBalance(a.getBalance() + o.getAmount());
            b.setBalance(b.getBalance() - o.getAmount());

            o.setFromAccount(accountRepository.save(a));
            o.setToAccount(accountRepository.save(b));
        } else {
            if (o.getOperationType().equals(OperationType.OUTCOME)) {
                a.setBalance(a.getBalance() + o.getAmount());
            } else {
                a.setBalance(a.getBalance() - o.getAmount());
            }

            o.setFromAccount(accountRepository.save(a));
        }

        operationRepository.deleteById(operationId);
    }

    @Override
    public OperationDto getOperationByAccountIdAndOperationId(UUID accountId, UUID operationId) {
        return null;
    }
}
