package com.example.final_project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {
    private double balance;
    private String accountType;
    private Integer accountNumber;
    private Long accountUserId;

    public AccountDTO(double balance, String accountType, Integer accountNumber, Long accountUserId) {
        this.balance = balance;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.accountUserId = accountUserId;
    }

    public AccountDTO() {}
}
