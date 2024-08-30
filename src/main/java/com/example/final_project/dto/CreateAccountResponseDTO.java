package com.example.final_project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateAccountResponseDTO {
    private Integer accountNumber;
    private Integer sortCode;
    private String accountName;
    private Double balance;
    private Long customerId;

    public CreateAccountResponseDTO(Integer accountNumber, Integer sortCode, String accountName, Double balance, Long customerId) {
        this.accountNumber = accountNumber;
        this.sortCode = sortCode;
        this.accountName = accountName;
        this.balance = balance;
        this.customerId = customerId;
    }
}