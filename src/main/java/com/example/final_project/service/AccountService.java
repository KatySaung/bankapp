package com.example.final_project.service;

import com.example.final_project.dto.AccountDTO;
import com.example.final_project.entities.Account;
import com.example.final_project.entities.User;
import com.example.final_project.repository.AccountRepository;
import com.example.final_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    public AccountDTO getAccountByNumber(int accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        return new AccountDTO(account.getBalance(), account.getAccountType(), account.getAccountNumber(), account.getUser().getUserId());
    }


    public AccountDTO createAccount(AccountDTO accountDTO) {
        User user = userRepository.findById(accountDTO.getAccountUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Account account = new Account();
        account.setBalance(accountDTO.getBalance());
        account.setAccountType(accountDTO.getAccountType());
        account.setAccountNumber(accountDTO.getAccountNumber());
        account.setUser(user);
        accountRepository.save(account);
        return new AccountDTO(account.getBalance(), account.getAccountType(), account.getAccountNumber(), user.getUserId());
    }

    public double deleteAccount(Integer accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        double finalBalance = account.getBalance();
        accountRepository.delete(account);
        return finalBalance;
    }
}
