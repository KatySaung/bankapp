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

    public AccountDTO(String accountType, Long accountCustomerId, double balance) {
        this.accountType = accountType;
        this.accountCustomerId = accountCustomerId;
        this.balance = balance;
        //this.accountNumber//generate random number;
        //this.routNum//generate random number;
    }

    public AccountDTO() {}
}
