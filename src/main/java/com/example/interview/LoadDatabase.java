package com.example.interview;

import com.example.interview.account.Account;
import com.example.interview.account.AccountRepository;
import com.example.interview.account.AccountType;
import com.example.interview.customer.Customer;
import com.example.interview.customer.CustomerRepository;
import com.example.interview.transaction.Transaction;
import com.example.interview.transaction.TransactionRepository;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.slf4j.Logger;

import java.math.BigDecimal;

@Configuration
class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(
            CustomerRepository customerRepository,
            AccountRepository accountRepository,
            TransactionRepository transactionRepository) {

        Customer customer1 = new Customer("first 1", "last 1");
        Customer customer2 = new Customer("first 2", "last 2");
        Customer customer3 = new Customer("first 3", "last 3");
        Customer customer4 = new Customer("first 4", "last 4");

        Account account1 = new Account(new BigDecimal("100.50"), true, AccountType.CURRENT);
        Account account2 = new Account(new BigDecimal("200.50"), false, AccountType.SAVINGS);
        Account account3 = new Account(new BigDecimal("300.50"), true, AccountType.CURRENT);
        Account account4 = new Account(new BigDecimal("400.50"), true, AccountType.SAVINGS);
        Account account5 = new Account(new BigDecimal("500.50"), false, AccountType.CURRENT);
        Account account6 = new Account(new BigDecimal("600.50"), true, AccountType.SAVINGS);

        customer1.addAccount(account1);

        customer2.addAccount(account2);
        customer2.addAccount(account3);

        customer3.addAccount(account3);
        customer3.addAccount(account4);
        customer3.addAccount(account5);

        customer4.addAccount(account1);
        customer4.addAccount(account2);
        customer4.addAccount(account6);

        Transaction transaction1 = new Transaction(BigDecimal.valueOf(10.10), account1, account2);
        Transaction transaction2 = new Transaction(BigDecimal.valueOf(20.10), account2, account3);
        Transaction transaction3 = new Transaction(BigDecimal.valueOf(30.10), account3, account4);
        Transaction transaction4 = new Transaction(BigDecimal.valueOf(40.10), account4, account5);
        Transaction transaction5 = new Transaction(BigDecimal.valueOf(50.10), account5, account6);
        Transaction transaction6 = new Transaction(BigDecimal.valueOf(60.10), account6, account1);
        Transaction transaction7 = new Transaction(BigDecimal.valueOf(70.10), account5, account2);
        Transaction transaction8 = new Transaction(BigDecimal.valueOf(80.10), account4, account3);
        Transaction transaction9 = new Transaction(BigDecimal.valueOf(90.10), account3, account4);
        Transaction transaction10 = new Transaction(BigDecimal.valueOf(100.10), account2, account5);

        return args -> {
            log.info("Preloading " + accountRepository.save(account1));
            log.info("Preloading " + accountRepository.save(account2));
            log.info("Preloading " + accountRepository.save(account3));
            log.info("Preloading " + accountRepository.save(account4));
            log.info("Preloading " + accountRepository.save(account5));
            log.info("Preloading " + accountRepository.save(account6));
            log.info("Preloading " + customerRepository.save(customer1));
            log.info("Preloading " + customerRepository.save(customer2));
            log.info("Preloading " + customerRepository.save(customer3));
            log.info("Preloading " + customerRepository.save(customer4));
            log.info("Preloading " + transactionRepository.save(transaction1));
            log.info("Preloading " + transactionRepository.save(transaction2));
            log.info("Preloading " + transactionRepository.save(transaction3));
            log.info("Preloading " + transactionRepository.save(transaction4));
            log.info("Preloading " + transactionRepository.save(transaction5));
            log.info("Preloading " + transactionRepository.save(transaction6));
            log.info("Preloading " + transactionRepository.save(transaction7));
            log.info("Preloading " + transactionRepository.save(transaction8));
            log.info("Preloading " + transactionRepository.save(transaction9));
            log.info("Preloading " + transactionRepository.save(transaction10));
        };
    }
}