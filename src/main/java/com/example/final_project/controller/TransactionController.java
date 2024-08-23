package com.example.final_project.controller;

import com.example.final_project.entities.Transaction;
import com.example.final_project.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
 private TransactionService transactionService;
    @PostMapping
public ResponseEntity<List<Transaction>> createTransaction(@RequestBody Transaction transaction) {
        List<Transaction> savedTransactions = transactionService.createTransaction(transaction);
        return ResponseEntity.ok(savedTransactions);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }
}

