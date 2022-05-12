package com.example.interview.customer;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends CrudRepository<Customer, UUID> {

    @Query("SELECT DISTINCT c FROM Customer c LEFT JOIN c.accounts ca ON ca.active = :active")
    @EntityGraph(value = "graph.Customer.accounts")
    List<Customer> findAllWithAccountsByActive(boolean active);

}
