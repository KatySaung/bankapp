package com.example.final_project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountResponseDto {
    private Integer accountNumber;
    private Integer routingNumber;
    private String accountName;
    private Double balance;
    private Long customerId;

    public CreateAccountResponseDto(Integer accountNumber, Integer routingNumber, String accountName, Double balance, Long customerId) {
        this.accountNumber = accountNumber;
        this.routingNumber = routingNumber;
        this.accountName = accountName;
        this.balance = balance;
        this.customerId = customerId;
    }
}
