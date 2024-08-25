package com.example.final_project.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "accounts", schema = "banking")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double balance;

    @Column(nullable = false)
    private String accountType;

    @Column(nullable = false, unique = true)
    private int accountNumber;

    @ManyToOne(optional = false)
    @JoinColumn(name = "account_user_id", nullable = false)
    private User user;

    public Account(double balance, String accountType, Integer accountNumber, User user) {
        this.balance = balance;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.user = user;
    }

    public Account() {}
}
