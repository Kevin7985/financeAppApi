package com.istech.accounts;

import com.istech.accounts.dto.AccountDto;

import java.util.UUID;

public interface AccountService {
    AccountDto getAccountById(UUID accountId);
}
