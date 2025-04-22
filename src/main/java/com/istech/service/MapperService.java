package com.istech.service;

import com.istech.accounts.dto.AccountDto;
import com.istech.accounts.dto.AccountMapper;
import com.istech.accounts.models.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MapperService {
    private final AccountMapper accountMapper;

    public AccountDto toAccountDto(Account account) {
        return accountMapper.toAccountDto(account);
    }
}
