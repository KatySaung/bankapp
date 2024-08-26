package com.example.final_project.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateAccountRequestDto {
    private Integer customerId;
    private String accountName;
    private Double openingBalance;

}
