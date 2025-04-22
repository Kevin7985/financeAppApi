package com.istech.accounts.dto;

import com.istech.accounts.models.Account;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AccountMapper {
    public Account toAccount(InputAccountDto accountDto) {
        return new Account(
                null,
                accountDto.getTitle(),
                0L,
                LocalDateTime.now()
        );
    }

    public AccountDto toAccountDto(Account account) {
        return new AccountDto(
                account.getId(),
                account.getTitle(),
                account.getBalance(),
                account.getCreatedAt()
        );
    }
}
