package com.example.final_project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private Long transactionId;
    private String transactionType;
    private Integer fromAccountNumber;
    private Integer toAccountNumber;
    private Double amount;
    private LocalDateTime timestamp;

}
