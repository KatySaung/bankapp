package com.example.final_project.dto;

import java.util.List;


public record AccountDTO(
        Integer number,
        Integer sortCode,
        String name,
        Double openingBalance,
        List<TransactionDTO> transactions,
        Double balance,
        Long customerId
) {}

