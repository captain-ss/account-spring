package com.example.demo;

import com.example.demo.model.Transaction;
import com.example.demo.model.User;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utility.TransactionType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Calendar;
import java.util.Date;
@SpringBootApplication
public class AccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(UserRepository userRepository, TransactionRepository transactionRepository) {
        return args -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2000, Calendar.NOVEMBER, 10);
            final Date date = calendar.getTime();
            User user = new User("name", "email.com", "username", "img", "911", Integer.valueOf(100), date, Long.valueOf("100"), "password");
            userRepository.save(user);

            final Transaction transaction = new Transaction(user, Integer.valueOf(1000), date, TransactionType.EXPENSE);
            transactionRepository.save(transaction);
        };
    }
}