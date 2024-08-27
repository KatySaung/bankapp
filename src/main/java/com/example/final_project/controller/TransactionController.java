package com.example.final_project.controller;

import com.example.final_project.dto.TransactionDTO;
import com.example.final_project.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
@CrossOrigin

public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        TransactionDTO newTransaction = transactionService.createTransaction(transactionDTO);
        return ResponseEntity.ok(newTransaction);
    }
}

