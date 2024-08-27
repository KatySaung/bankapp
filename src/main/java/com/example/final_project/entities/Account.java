package com.example.final_project.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts", schema = "banking")
@Data
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "account_number", nullable = false, unique = true)
    private Integer accountNumber;

    @Column(name = "routing_number", nullable = false)
    private Integer routingNumber;

    @Column(name = "account_name", nullable = false)
    private String accountName;

    @Column(name = "account_type", nullable = false)
    private String accountType;

    @Column(name = "balance", nullable = false)
    private Double balance;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Account(Integer accountNumber, Integer routingNumber, String accountName, String accountType, Double balance, Customer customer) {
        this.accountNumber = accountNumber;
        this.routingNumber = routingNumber;
        this.accountName = accountName;
        this.accountType = accountType;
        this.balance = balance;
        this.customer = customer;
    }

    public String getAccountType() {
        return customer.
    }
}
