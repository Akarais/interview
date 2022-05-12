package com.example.interview.transaction;

import com.example.interview.View;
import com.example.interview.account.Account;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@JsonSerialize(using = TransactionSerializer.class)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "org.hibernate.type.UUIDCharType")
    @JsonView(View.Public.class)
    private UUID id;
    @Column(precision = 12, scale = 2)
    @JsonView(View.Public.class)
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "fk_from_account")
    @JsonView(View.Public.class)
    private Account from;
    @ManyToOne
    @JoinColumn(name = "fk_to_account")
    @JsonView(View.Public.class)
    private Account to;

    protected Transaction() {}

    public Transaction(BigDecimal amount, Account from, Account to) {
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

    public Account getFrom() {
        return from;
    }

    public void setFrom(Account from) {
        this.from = from;
    }

    public Account getTo() {
        return to;
    }

    public void setTo(Account to) {
        this.to = to;
    }

    public UUID getId() {
        return this.id;
    }
}
