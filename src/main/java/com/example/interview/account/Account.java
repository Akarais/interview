package com.example.interview.account;

import com.example.interview.customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    @Column(precision = 12, scale = 2)
    private BigDecimal balance;
    private boolean active;

    @ManyToMany(mappedBy = "accounts")
    @JsonIgnore
    private Set<Customer> customers = new HashSet<>();

    @Enumerated(EnumType.ORDINAL)
    private AccountType type;

    protected Account() {}

    public Account(BigDecimal balance, boolean active, AccountType type) {
        this.balance = balance;
        this.active = active;
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id;
    }
}
