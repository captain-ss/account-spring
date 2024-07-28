package com.example.demo.model;

import com.example.demo.utility.TransactionType;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, unique = false)
    private Integer amount;

    @Column(nullable = false, unique = false)
    private Date transaction_date;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    public Transaction() {
    }

    public Transaction(User user, Integer amount, Date transaction_date, TransactionType transactionType) {
        this.user = user;
        this.amount = amount;
        this.transaction_date = transaction_date;
        this.transactionType = transactionType;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", user=" + user +
                ", amount=" + amount +
                ", transaction_date=" + transaction_date +
                ", transactionType=" + transactionType +
                '}';
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Integer getAmount() {
        return amount;
    }

    public Date getTransaction_date() {
        return transaction_date;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public void setTransaction_date(Date transaction_date) {
        this.transaction_date = transaction_date;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
