package com.example.final_project.controller;

import com.example.final_project.dto.AccountDTO;
import com.example.final_project.dto.CreateAccountRequestDTO;
import com.example.final_project.dto.CreateAccountResponseDto;
import com.example.final_project.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@CrossOrigin
public class AccountController {

    @Autowired
    private AccountService accountService;

    // New endpoint to fetch all accounts
    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        List<AccountDTO> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountDTO> getAccountByNumber(@PathVariable Integer accountNumber) {
        AccountDTO account = accountService.getAccountByNumber(accountNumber);
        return ResponseEntity.ok(account);
    }

    @PostMapping
    public ResponseEntity<CreateAccountResponseDto> createAccount(@RequestBody CreateAccountRequestDTO requestDto) {
        CreateAccountResponseDto newAccount = accountService.createAccount(requestDto);
        return ResponseEntity.status(201).body(newAccount);
    }

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<Double> deleteAccount(@PathVariable Integer accountNumber) {
        double finalBalance = accountService.deleteAccount(accountNumber);
        return ResponseEntity.ok(finalBalance);
    }
}
