package com.example.interview.transaction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(precision = 12, scale = 2)
    private BigDecimal amount;
    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
    private UUID from;
    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
    private UUID to;

    protected Transaction() {}

    public Transaction(BigDecimal amount, UUID from, UUID to) {
        this.amount = amount;
        this.from = from;
        this.to = to;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public UUID getFrom() {
        return from;
    }

    public void setFrom(UUID from) {
        this.from = from;
    }

    public UUID getTo() {
        return to;
    }

    public void setTo(UUID to) {
        this.to = to;
    }
}
