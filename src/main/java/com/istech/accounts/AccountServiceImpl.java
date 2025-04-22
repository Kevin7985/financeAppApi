package com.istech.accounts;

import com.istech.accounts.dto.AccountDto;
import com.istech.accounts.exceptions.AccountNotFoundException;
import com.istech.accounts.models.Account;
import com.istech.service.MapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final MapperService mapperService;
    private final AccountRepository accountRepository;

    @Override
    public AccountDto getAccountById(UUID accountId) {
        Account acc = getAccountById_db(accountId);
        return mapperService.toAccountDto(acc);
    }

    private Account getAccountById_db(UUID accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Счёт с данным ID не найден"));
    }
}