package com.example.final_project.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountDTO {
    private double balance;
    private String accountType;
    private int accountNumber;

    //constructor
    public AccountDTO(double balance, String accountType, int accountNumber) {
        this.balance = balance;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
    }

    //default constructor
    public AccountDTO(){}

}
