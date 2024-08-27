package com.example.final_project.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionRequestDTO {

    private Integer type;
    private Integer fromAccount;
    private Integer fromAccountSortCode;
    private Integer toAccount;
    private Integer toAccountSortCode;
    private Double amount;

}
