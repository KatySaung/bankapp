package com.example.final_project.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionDTO {
    private Long id;
    private Integer fromAccountNumber;
    private Integer toAccountNumber;
    private Double amount;
    private String transactionType;
    private LocalDateTime timestamp;

    public TransactionDTO(Long id, Integer fromAccountNumber, Integer toAccountNumber, Double amount, String transactionType, LocalDateTime timestamp) {
        this.id = id;
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.amount = amount;
        this.transactionType = transactionType;
        this.timestamp = timestamp;
    }

    public TransactionDTO() {}
}

