package com.example.final_project.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class TransactionDTO {
    private Long id;
    private Long fromAccountId;
    private Long toAccountId;
    private Double amount;
    private String transactionType;
    private LocalDateTime timestamp;
    // getters and setters


    public TransactionDTO(Long id, Long fromAccountId, Long toAccountId, Double amount, String transactionType, LocalDateTime timestamp) {
        this.id = id;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.transactionType = transactionType;
        this.timestamp = timestamp;
    }
    public TransactionDTO() {
    }
}
