package com.example.interview.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/customer")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping(path="/")
    public @ResponseBody boolean addNewCustomer(
            @RequestParam String firstName,
            @RequestParam String lastName) {
        Customer customer = new Customer(firstName, lastName);
        customerRepository.save(customer);
        return true;
    }

    @GetMapping(path="/")
    public @ResponseBody ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerRepository.findAllWithAccounts();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
