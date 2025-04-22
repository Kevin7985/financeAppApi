package com.istech.accounts.dto;

import com.istech.accounts.models.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public AccountDto toAccountDto(Account account) {
        return new AccountDto(
                account.getId(),
                account.getTitle(),
                account.getBalance(),
                account.getCreatedAt()
        );
    }
}
