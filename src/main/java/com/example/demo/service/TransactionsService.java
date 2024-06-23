package com.example.demo.service;

import com.example.demo.model.Transaction;

import java.util.List;

public interface TransactionsService {
    List<Transaction> getAllTransactions();
    Transaction createTransaction(Transaction transaction);
}
