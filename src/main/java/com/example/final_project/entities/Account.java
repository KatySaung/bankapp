package com.example.final_project.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Random;

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
    public Account() {}
    public Account(
            Long id,
            double balance,
            String accountType,
            int accountNumber,
            User user) {
        this.id = id;
        this.balance = balance;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.user = user;
    }


}

