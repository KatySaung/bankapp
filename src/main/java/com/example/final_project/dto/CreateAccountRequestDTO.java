package com.example.final_project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountRequestDTO {
    private Long customerId;
    private String accountName;
    private Double openingBalance;
}
