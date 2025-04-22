package com.istech.accounts.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.istech.accounts.exceptions.AccountValidationException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputAccountDto {
    @NotNull(message = "Название счёта не может быть пустым")
    @NotBlank(message = "Название счёта не может быть пустым")
    @Size(min = 1, max = 255, message = "Название счёта не может быть пустым или длинее 255 символов")
    @JsonProperty("title")
    private String title;
}
