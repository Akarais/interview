package com.example.interview.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(path="/customer")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping(path="/")
    public @ResponseBody Customer createCustomer(
            @RequestParam String firstName,
            @RequestParam String lastName) {
        Customer customer = new Customer(firstName, lastName);
        return customerRepository.save(customer);
    }

    @PutMapping(path="/{customerId}")
    public @ResponseBody Customer updateCustomer(
            @PathVariable String customerId,
            @RequestParam String firstName,
            @RequestParam String lastName) {
        Customer customer =
                customerRepository
                        .findById(UUID.fromString(customerId))
                        .orElseThrow(() -> new EntityNotFoundException(customerId));
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        return customerRepository.save(customer);
    }

//    @GetMapping(path="/{customer_id}/savings")
//    public @ResponseBody BigDecimal getTotalSavings() {
//
//    }

    @GetMapping(path="/inactive_accounts")
    public @ResponseBody ResponseEntity<List<Customer>> getCustomersWithInactiveAccounts() {
        List<Customer> customers = customerRepository.findAllWithAccountsByActive(false);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
