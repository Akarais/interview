package com.example.interview.transaction;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends CrudRepository<Transaction, UUID> {

    @Query("FROM Transaction t WHERE t.amount > :amount")
    List<Transaction> getAllExceedingAmount(BigDecimal amount);
}
