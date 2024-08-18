package com.example.demo.payload;

import jakarta.validation.constraints.NotEmpty;

public record LoginUserPayload(
        @NotEmpty(message = "Username is required")
        String username,
        @NotEmpty(message = "Password is required")
        String password
) {
}
