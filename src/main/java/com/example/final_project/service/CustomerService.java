package com.example.final_project.service;

import com.example.final_project.dto.AccountDTO;
import com.example.final_project.dto.CustomerDTO;
import com.example.final_project.entities.Account;
import com.example.final_project.entities.Customer;
import com.example.final_project.repository.AccountRepository;
import com.example.final_project.repository.CustomerRepository;
import com.example.final_project.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public Customer registerDTO(String name) {
        // Removed validation logic here
        if (customerRepository.findByUsername(name) != null) {
            throw new IllegalArgumentException("Username already exists.");
        }

        Customer customer = new Customer(name);
        return customerRepository.save(customer);
    }

    @Transactional(readOnly = true)
    public CustomerDTO getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToCustomerDTO(customer);
    }

    private CustomerDTO convertToCustomerDTO(Customer customer) {
        List<AccountDTO> accountDTOs = customer.getAccounts().stream()
                .map(account -> new AccountDTO(
                        account.getAccountType(),
                        customer.getCustomerId(),
                        account.getBalance()
                ))
                .collect(Collectors.toList());

        return new CustomerDTO(customer.getCustomerId(), customer.getUsername(), accountDTOs);
    }

    @Transactional
    public double deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("User ID not found."));

        List<Account> accounts = customer.getAccounts();

        for (Account account : accounts) {
            transactionRepository.deleteByFromAccountNumber(account.getAccountNumber());
            transactionRepository.deleteByToAccountNumber(account.getAccountNumber());
        }

        double totalFunds = accounts.stream()
                .mapToDouble(Account::getBalance)
                .sum();
        accountRepository.deleteAll(accounts);
        customerRepository.delete(customer);

        return totalFunds;
    }

    @Transactional(readOnly = true)
    public List<CustomerDTO> findAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(this::convertToCustomerDTO)
                .collect(Collectors.toList());
    }

}
