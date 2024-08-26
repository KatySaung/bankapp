package com.example.final_project.service;

import com.example.final_project.dto.AccountDTO;
import com.example.final_project.dto.UserDTO;
import com.example.final_project.entities.Account;
import com.example.final_project.entities.Transaction;
import com.example.final_project.entities.User;
import com.example.final_project.repository.AccountRepository;
import com.example.final_project.repository.TransactionRepository;
import com.example.final_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public User registerDTO(String name) {
        // Removed validation logic here
        if (userRepository.findByUsername(name) != null) {
            throw new IllegalArgumentException("Username already exists.");
        }

        User user = new User(name);
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToUserDTO(user);
    }

    private UserDTO convertToUserDTO(User user) {
        List<AccountDTO> accountDTOs = user.getAccounts().stream()
                .map(account -> new AccountDTO(
                        account.getBalance(),
                        account.getAccountType(),
                        account.getAccountNumber(),
                        user.getUserId()
                ))
                .collect(Collectors.toList());

        return new UserDTO(user.getUserId(), user.getUsername(), accountDTOs);
    }

    @Transactional
    public double deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User ID not found."));

        List<Account> accounts = user.getAccounts();

        for (Account account : accounts) {
            transactionRepository.deleteByFromAccountNumber(account.getAccountNumber());
            transactionRepository.deleteByToAccountNumber(account.getAccountNumber());
        }

        double totalFunds = accounts.stream()
                .mapToDouble(Account::getBalance)
                .sum();
        accountRepository.deleteAll(accounts);
        userRepository.delete(user);

        return totalFunds;
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToUserDTO)
                .collect(Collectors.toList());
    }
}
