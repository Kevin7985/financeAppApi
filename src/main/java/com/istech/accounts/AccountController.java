package com.istech.accounts;

import com.istech.accounts.dto.AccountDto;
import com.istech.accounts.dto.InputAccountDto;
import com.istech.utils.models.ListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
@Tag(name = "Accounts", description = "Методы для работы с счетами")
@CrossOrigin("*")
@Validated
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    @Operation(summary = "Создание нового счёта")
    public AccountDto createAccount(@RequestBody @Valid InputAccountDto accountDto) {
        return accountService.createAccount(accountDto);
    }

    @GetMapping("/{accountId}")
    @Operation(summary = "Получение счёта по ID")
    public AccountDto getAccountById(@PathVariable UUID accountId) {
        return accountService.getAccountById(accountId);
    }

    @GetMapping
    @Operation(summary = "Список всех счетов")
    public ListResponse<AccountDto> searchAccounts(
            @RequestParam(required = false, defaultValue = "") String q,
            @RequestParam(required = false, defaultValue = "0") Long offset,
            @RequestParam(required = false, defaultValue = "20") Integer limit) {
        return accountService.searchAccounts(q, offset, limit);
    }
}
