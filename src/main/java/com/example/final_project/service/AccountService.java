package com.example.final_project.service;

import com.example.final_project.dto.AccountDTO;
import com.example.final_project.entities.Account;
import com.example.final_project.entities.User;
import com.example.final_project.repository.AccountRepository;
import com.example.final_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private AtomicInteger nextId = new AtomicInteger(1);

    public int generateUniqueId(){
        return nextId.getAndIncrement();
    }

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    public AccountDTO getAccountByNumber(int accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        return new AccountDTO(account.getBalance(), account.getAccountType(), account.getAccountNumber(), account.getUser().getUserId());
    }

    //create a new account
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

    //update an existing account
    public AccountDTO updateAccount(Integer accountNumber, AccountDTO updatedAccountDTO) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(updatedAccountDTO.getBalance());
        account.setAccountType(updatedAccountDTO.getAccountType());

        accountRepository.save(account);

        return new AccountDTO(account.getBalance(), account.getAccountType(), account.getAccountNumber(), account.getUser().getUserId());
    }

    //delete an account
    public double deleteAccount(Integer accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        double finalBalance = account.getBalance();
        accountRepository.delete(account);
        return finalBalance;
    }

    //get all accounts
    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(account -> new AccountDTO(account.getBalance(), account.getAccountType(), account.getAccountNumber(), account.getUser().getUserId()))
                .collect(Collectors.toList());
    }
}
