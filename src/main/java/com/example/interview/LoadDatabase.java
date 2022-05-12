package com.example.interview;

import com.example.interview.account.Account;
import com.example.interview.account.AccountRepository;
import com.example.interview.account.AccountType;
import com.example.interview.customer.Customer;
import com.example.interview.customer.CustomerRepository;
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
    CommandLineRunner initDatabase(CustomerRepository repository, AccountRepository repository2) {
        Customer customer1 = new Customer("first 1", "last 1");
        Customer customer2 = new Customer("first 2", "last 2");
        Customer customer3 = new Customer("first 3", "last 3");
        Customer customer4 = new Customer("first 4", "last 4");

        Account account1 = new Account(new BigDecimal("100.50"), true, AccountType.CURRENT);
        Account account2 = new Account(new BigDecimal("200.50"), false, AccountType.SAVINGS);
        Account account3 = new Account(new BigDecimal("300.50"), true, AccountType.CURRENT);
        Account account4 = new Account(new BigDecimal("400.50"), false, AccountType.SAVINGS);
        Account account5 = new Account(new BigDecimal("500.50"), false, AccountType.CURRENT);
        Account account6 = new Account(new BigDecimal("600.50"), false, AccountType.SAVINGS);

        customer1.addAccount(account1);

        customer2.addAccount(account2);
        customer2.addAccount(account3);

        customer3.addAccount(account4);
        customer3.addAccount(account5);

        customer4.addAccount(account1);
        customer4.addAccount(account2);
        customer4.addAccount(account6);

        return args -> {
            log.info("Preloading " + repository2.save(account1));
            log.info("Preloading " + repository2.save(account2));
            log.info("Preloading " + repository2.save(account3));
            log.info("Preloading " + repository2.save(account4));
            log.info("Preloading " + repository2.save(account5));
            log.info("Preloading " + repository2.save(account6));
            log.info("Preloading " + repository.save(customer1));
            log.info("Preloading " + repository.save(customer2));
            log.info("Preloading " + repository.save(customer3));
            log.info("Preloading " + repository.save(customer4));
        };
    }
}