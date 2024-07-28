package com.example.demo.service;

import com.example.demo.model.Transaction;
import com.example.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionsServiceImpl implements TransactionsService{
    @Autowired
    TransactionRepository transactionRepository;
    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> result = new ArrayList<Transaction>();
        System.out.println(result);
        try{
            this.transactionRepository.findAll().forEach(transaction -> {
                System.out.println(transaction.toString());
            });
            this.transactionRepository.findAll().forEach(result::add);
            return result;
        }catch (Exception e){
            System.out.println(e.toString());
            throw e;
        }
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return this.transactionRepository.save(transaction);
    }
}
