package com.example.final_project.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "accounts")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //setter/ getter
    @Setter
    @Getter
    private double balance;
    @Setter
    @Getter
    private String accountType;
    @Setter
    @Getter
    private int accountNumber;

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //constructor

    public Account(double balance, String accountType, int accountNumber) {
        this.balance = balance;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
    }
    public Account(){}

}

