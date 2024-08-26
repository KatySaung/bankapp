package com.example.final_project.controller;

import com.example.final_project.dto.AccountDTO;
import com.example.final_project.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@CrossOrigin
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("account/{accountNumber}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable Integer accountNumber) {
        AccountDTO account = accountService.getAccountByNumber(accountNumber);
        return ResponseEntity.ok(account);
    }

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
        AccountDTO newAccount = accountService.createAccount(accountDTO);
        return ResponseEntity.status(201).body(newAccount);
    }

    @DeleteMapping("account/{accountNumber}")
    public ResponseEntity<Double> deleteAccount(@PathVariable Integer accountNumber) {
        double finalBalance = accountService.deleteAccount(accountNumber);
        return ResponseEntity.ok(finalBalance);
    }
}
