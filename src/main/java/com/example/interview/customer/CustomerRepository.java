package com.example.interview.customer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends CrudRepository<Customer, UUID> {

    @Query("FROM Customer c LEFT JOIN FETCH c.accounts")
    List<Customer> findAllWithAccounts();

}
