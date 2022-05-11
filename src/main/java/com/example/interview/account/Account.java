package com.example.interview.account;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(precision = 12, scale = 2)
    private BigDecimal balance;
    private boolean active;
    @Column(name = "customer_id")
    @OneToOne(mappedBy = "customer", fetch = FetchType.LAZY)
    private UUID customerId;
    @Enumerated(EnumType.ORDINAL)
    private AccountType type;

    protected Account() {}

    public Account(BigDecimal balance, boolean active, UUID customerId, AccountType type) {
        this.balance = balance;
        this.active = active;
        this.customerId = customerId;
        this.type = type;
    }

    public UUID getId() {
        return id;
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

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }
}
