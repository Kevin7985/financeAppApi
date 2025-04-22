package com.istech.accounts;

import com.istech.accounts.dto.AccountDto;
import com.istech.accounts.dto.InputAccountDto;

import java.util.UUID;

public interface AccountService {
    AccountDto createAccount(InputAccountDto accountDto);
    AccountDto getAccountById(UUID accountId);
}
