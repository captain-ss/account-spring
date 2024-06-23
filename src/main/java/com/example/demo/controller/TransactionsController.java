package com.example.demo.controller;

import com.example.demo.model.Transaction;
import com.example.demo.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "transactions")
public class TransactionsController {
    @Autowired
    private TransactionsService transactionsService;

    @GetMapping(path = {"", "/"})
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        List<Transaction> transactions = this.transactionsService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }
}
