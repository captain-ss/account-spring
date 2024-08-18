package com.example.demo.controller;

import com.example.demo.exceptions.user.UserNotFoundException;
import com.example.demo.model.Transaction;
import com.example.demo.model.User;
import com.example.demo.payload.CreateTransactionPayload;
import com.example.demo.service.TransactionsService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "transactions")
public class TransactionsController {
    @Autowired
    private TransactionsService transactionsService;
    @Autowired
    private UserService userService;

    @GetMapping(path = {"", "/"})
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userService.getUserByUsername(username);
        List<Transaction> transactions = this.transactionsService.getAllTransactionsByUserId(user.getId());
        System.out.println(transactions.toString());
        return ResponseEntity.ok(transactions);
    }

    @PostMapping(path = "create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody CreateTransactionPayload transactionPayload){
        System.out.println(transactionPayload.toString());
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            User user = userService.getUserByUsername(username);
            System.out.println(user.toString());
            Calendar calendar = Calendar.getInstance();
            calendar.set(2000, Calendar.NOVEMBER, 10);
            final Date date = calendar.getTime();
            Transaction transaction = new Transaction(
                    user,
                    transactionPayload.amount(),
                    date,
                    transactionPayload.transactionType()
            );
            this.transactionsService.createTransaction(transaction);
            return new ResponseEntity<Transaction>(transaction, HttpStatus.CREATED);
        } catch (UserNotFoundException e){
          throw e;
        } catch (Exception e){
            System.out.println(e.toString());
            throw e;
        }
    }
}
