package com.example.final_project.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name = "accounts", schema = "banking")
@Data
public class Account {
//    private AtomicInteger accId = new AtomicInteger(1);
//
//    private AtomicInteger routId = new AtomicInteger(1);
//
//    public int generateUniqueAccId(){
//        return accId.getAndIncrement();
//    }
//
//    public int generateUniqueRoutId(){
//        return routId.getAndIncrement();
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double balance;

    @Column(nullable = false)
    private String accountType;

    @Column(nullable = false, unique = true)
    private int accountNumber;

//    @Column(nullable = false, unique = true)
//    private int routNum;

    @ManyToOne(optional = false)
    @JoinColumn(name = "account_customer_id", nullable = false)
    private Customer customer;

    public Account(double balance, String accountType, Integer accountNumber, Customer customer) {
        this.balance = balance;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        //this.routNum = routNum;
        this.customer = customer;
    }

//    public Account(double balance, String accountType, Customer customer) {
//        this.balance = balance;
//        this.accountType = accountType;
//        this.accountNumber = generateUniqueAccId();
//        this.routNum = generateUniqueRoutId();
//        this.customer = customer;
//    }

    public Account() {}
}
