package com.example.demo.payload;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public record CreateUserPayload(
    @NotEmpty(message = "Name is required")
    String name,
    @NotEmpty(message = "Email is required")
    @Email(message = "Must be a valid email")
    String email,
    @NotEmpty(message = "Username is required")
    String username,
    String image_id,
    @NotEmpty(message = "Phone number can not be null")
    String phone,
    @NotNull(message = "Monthly salary is required")
    @Min(value = 1, message = "Monthly salary must be greater than 0")
    Integer monthly_salary,
    @NotNull(message = "Salary credit date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date salary_credit_date,
    Long account_balance,
    @NotEmpty(message = "Password is required")
    String password
) {
    public CreateUserPayload{

    }
}
