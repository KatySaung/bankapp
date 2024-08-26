package com.example.final_project.service;

import com.example.final_project.dto.AccountDTO;
import com.example.final_project.dto.CustomerDTO;
import com.example.final_project.entities.Account;
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
public class CustomerService {

    @Autowired
    private CustomerRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public Customer registerDTO(String name) {
        // Removed validation logic here
        if (userRepository.findByCustomername(name) != null) {
            throw new IllegalArgumentException("Username already exists.");
        }

        Customer customer = new Customer(name);
        return userRepository.save(customer);
    }

    @Transactional(readOnly = true)
    public CustomerDTO getCustomerById(Long customerId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToUserDTO(user);
    }

    private CustomerDTO convertToUserDTO(User user) {
        List<AccountDTO> accountDTOs = user.getAccounts().stream()
                .map(account -> new AccountDTO(
                        account.getBalance(),
                        account.getAccountType(),
                        account.getAccountNumber(),
                        user.getUserId()
                ))
                .collect(Collectors.toList());

        return new CustomerDTO(user.getUserId(), user.getUsername(), accountDTOs);
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
    public List<CustomerDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToUserDTO)
                .collect(Collectors.toList());
    }
}