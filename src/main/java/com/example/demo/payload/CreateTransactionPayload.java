package com.example.demo.payload;

import com.example.demo.utility.TransactionType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

public record CreateTransactionPayload(
    @NotNull(message = "Transaction amount is required")
    Integer amount,
    @NotNull(message = "Transaction date is required")
    Date transaction_date,
    @NotNull(message = "Transaction type is required")
    TransactionType transactionType
) {
}
