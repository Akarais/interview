package com.example.interview.customer;

import com.example.interview.View;
import com.example.interview.account.Account;
import com.example.interview.account.AccountType;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
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
    @JsonView(View.Internal_2.class)
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

    @GetMapping(path="/{customerId}/savings")
    public @ResponseBody ResponseEntity<BigDecimal> getCustomerTotalSavings(
            @PathVariable String customerId
    ) {
        Customer customer =
                customerRepository
                        .findById(UUID.fromString(customerId))
                        .orElseThrow(() -> new EntityNotFoundException(customerId));
        return new ResponseEntity<>(
                customer
                    .getAccounts()
                    .stream()
                    .filter(a -> a.getType() == AccountType.SAVINGS && a.isActive())
                    .map(Account::getBalance)
                    .reduce(BigDecimal.valueOf(0), BigDecimal::add),
                HttpStatus.OK
        );
    }

    @GetMapping(path="/inactive_accounts")
    @JsonView(View.Internal_2.class)
    public @ResponseBody ResponseEntity<List<Customer>> getCustomersWithInactiveAccounts() {
        List<Customer> customers = customerRepository.findAllWithAccountsByActive(false);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
