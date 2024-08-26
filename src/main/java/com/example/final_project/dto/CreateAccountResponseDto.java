package com.example.final_project.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateAccountResponseDto {
    private Integer number;
    private Integer sortCode;
    private String name;
    private Double openingBalance;
    private Integer[] transactions;
    private Double balance;
    private Integer customer;

    public CreateAccountResponseDto() {}
    public CreateAccountResponseDto(Integer number, Integer sortCode, String name, Double openingBalance, Integer[] transactions, Double balance, Integer customer) {
        this.number = number;
        this.sortCode = sortCode;
        this.name = name;
        this.openingBalance = openingBalance;
        this.transactions = transactions;
        this.balance = balance;
        this.customer = customer;
    }

}
