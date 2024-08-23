package com.example.final_project.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class TransactionDTO {
    private Long id;
    private Long fromAccountNumber;
    private Long toAccountNumber;
    private Double amount;
    private String transactionType;
    private LocalDateTime timestamp;
    // getters and setters


    public TransactionDTO(Long id, Long fromAccountNumber, Long toAccountNumber, Double amount, String transactionType, LocalDateTime timestamp) {
        this.id = id;
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.amount = amount;
        this.transactionType = transactionType;
        this.timestamp = timestamp;
    }
    public TransactionDTO() {
    }
}
