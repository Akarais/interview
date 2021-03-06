package com.example.interview.customer;

import com.example.interview.View;
import com.example.interview.account.Account;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NamedEntityGraph(name = "graph.Customer.accounts", attributeNodes = @NamedAttributeNode("accounts"))
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "org.hibernate.type.UUIDCharType")
    @JsonView(View.Public.class)
    private UUID id;
    @JsonView(View.Public.class)
    private String firstName;
    @JsonView(View.Public.class)
    private String lastName;

    @ManyToMany
    @JoinTable(
            name = "Customer_Account",
            joinColumns = { @JoinColumn(name = "customer_id") },
            inverseJoinColumns = { @JoinColumn(name = "account_id") }
    )
    @JsonView(View.Internal_2.class)
    private Set<Account> accounts = new HashSet<>();

    protected Customer() {}

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
        account.getCustomers().add(this);
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", accounts=" + accounts +
                '}';
    }


}
