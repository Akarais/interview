package com.example.interview.account;

import com.example.interview.customer.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AccountRepository extends CrudRepository<Customer, UUID> {

}
