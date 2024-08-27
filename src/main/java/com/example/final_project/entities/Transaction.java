package com.example.final_project.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions", schema = "banking")
@Data
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "from_account_number")
    private Integer fromAccountNumber;

    @Column(name = "to_account_number")
    private Integer toAccountNumber;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "transaction_type", nullable = false)
    private String transactionType;

    @Column(name = "transaction_timestamp", nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

    public Transaction(Integer fromAccountNumber, Integer toAccountNumber, Double amount, String transactionType, LocalDateTime timestamp) {
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.amount = amount;
        this.transactionType = transactionType;
        this.timestamp = timestamp != null ? timestamp : LocalDateTime.now();
    }
}
