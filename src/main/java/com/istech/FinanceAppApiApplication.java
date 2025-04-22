package com.istech;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Finance App API",
                version = "1.0.0"
        )
)
public class FinanceAppApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(FinanceAppApiApplication.class, args);
    }
}