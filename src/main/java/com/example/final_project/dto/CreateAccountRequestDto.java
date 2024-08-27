package com.example.final_project.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateAccountRequestDTO {
    private Integer number;
    private Integer routNum;
    private String accountName;
    private Double openingBalance;
    private Integer[] transactions;
    private Double balance;
    private Integer customerId;

    public CreateAccountRequestDTO(int i, int i1, String accountName, Double openingBalance, Integer[] integers, Double openingBalance1, Integer customerId) {
    }

}

