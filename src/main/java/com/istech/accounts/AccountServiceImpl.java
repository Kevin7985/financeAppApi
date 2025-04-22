package com.istech.accounts;

import com.istech.accounts.dto.AccountDto;
import com.istech.accounts.dto.InputAccountDto;
import com.istech.accounts.exceptions.AccountNotFoundException;
import com.istech.accounts.models.Account;
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
public class AccountServiceImpl implements AccountService {
    private final MapperService mapperService;
    private final AccountRepository accountRepository;

    @Override
    public AccountDto createAccount(InputAccountDto accountDto) {
        Account newAccount = mapperService.toAccount(accountDto);
        newAccount = accountRepository.save(newAccount);
        return mapperService.toAccountDto(newAccount);
    }

    @Override
    public AccountDto getAccountById(UUID accountId) {
        Account acc = getAccountById_db(accountId);
        return mapperService.toAccountDto(acc);
    }

    @Override
    public ListResponse<AccountDto> searchAccounts(String q, Long offset, Integer limit) {
        Pageable pageable = PageRequest.of((int) (offset / limit), limit, Sort.by("createdAt").descending());

        List<AccountDto> accounts;
        Long count = 0L;

        if (q == null) {
            accounts = accountRepository.findAll(pageable).stream()
                    .map(mapperService::toAccountDto)
                    .toList();
            count = accountRepository.count();
        } else {
            accounts = accountRepository.searchAccounts(q, pageable).stream()
                    .map(mapperService::toAccountDto)
                    .toList();
            count = accountRepository.searchAccountsCount(q);
        }

        return new ListResponse<>(
                count,
                accounts
        );
    }

    private Account getAccountById_db(UUID accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Счёт с данным ID не найден"));
    }
}