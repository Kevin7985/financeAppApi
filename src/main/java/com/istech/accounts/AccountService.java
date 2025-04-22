package com.istech.accounts;

import com.istech.accounts.dto.AccountDto;
import com.istech.accounts.dto.InputAccountDto;
import com.istech.utils.models.ListResponse;

import java.util.UUID;

public interface AccountService {
    AccountDto createAccount(InputAccountDto accountDto);
    AccountDto getAccountById(UUID accountId);
    ListResponse<AccountDto> searchAccounts(String q, Long offset, Integer limit);
}
