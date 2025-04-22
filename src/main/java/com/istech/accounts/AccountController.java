package com.istech.accounts;

import com.istech.accounts.dto.AccountDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
@Tag(name = "Accounts", description = "Методы для работы с счетами")
@CrossOrigin("*")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{accountId}")
    @Operation(summary = "Получение счёта по ID")
    public AccountDto getAccountById(@PathVariable UUID accountId) {
        return accountService.getAccountById(accountId);
    }
}
