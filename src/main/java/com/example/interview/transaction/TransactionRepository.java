package com.example.interview.transaction;

import com.example.interview.customer.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TransactionRepository extends CrudRepository<Customer, UUID> {
}
