package com.example.final_project.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "transactions")
@Data
public class Transaction {

    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long fromAccountNumber;

    @Column(nullable = true)
    private Long toAccountNumber;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String transactionType;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    // Constructors
    public Transaction() {}

    public Transaction(Long fromAccountNumber, Long toAccountNumber, Double amount, String transactionType) {
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.amount = amount;
        this.transactionType = transactionType;
        this.timestamp = LocalDateTime.now();
    }

}
