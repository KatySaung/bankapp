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

    public TransactionRequestDTO(Integer type, Integer fromAccount, Integer fromAccountSortCode, Integer toAccount, Integer toAccountSortCode, Double amount) {
        this.type = type;
        this.fromAccount = fromAccount;
        this.fromAccountSortCode = fromAccountSortCode;
        this.toAccount = toAccount;
        this.toAccountSortCode = toAccountSortCode;
        this.amount = amount;
    }

}
