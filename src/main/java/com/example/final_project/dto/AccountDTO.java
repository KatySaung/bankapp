package com.example.final_project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {
    private double balance;
    private String accountType;
    private Integer accountNumber;
    private Integer routNum;
    private Long accountCustomerId;

    public AccountDTO(double balance, String accountType, Integer accountNumber, Long accountCustomerId) {
        this.balance = balance;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        //this.routNum = routNum;
        this.accountCustomerId = accountCustomerId;
    }

    public AccountDTO() {}
}
