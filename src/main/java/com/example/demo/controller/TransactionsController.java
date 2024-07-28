package com.example.demo.controller;

import com.example.demo.model.Transaction;
import com.example.demo.payload.CreateTransactionPayload;
import com.example.demo.service.TransactionsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(path = "create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody CreateTransactionPayload transactionPayload){
        System.out.println(transactionPayload.toString());
        try {
            Transaction transaction = new Transaction();
            this.transactionsService.createTransaction(transaction);
        } catch (Exception e){
            System.out.println(e.toString());
        }
        return null;
    }
}
