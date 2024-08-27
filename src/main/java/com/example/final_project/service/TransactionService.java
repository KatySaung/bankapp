package com.example.final_project.service;

import com.example.final_project.dto.TransactionDTO;
import com.example.final_project.dto.TransactionRequestDTO;
import com.example.final_project.entities.Account;
import com.example.final_project.entities.Transaction;
import com.example.final_project.repository.AccountRepository;
import com.example.final_project.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public TransactionDTO processTransaction(TransactionRequestDTO request) {
        // Validate transaction type
        String transactionType = String.valueOf(request.getType());
        if (!"DEPOSIT".equals(transactionType) && !"WITHDRAWAL".equals(transactionType) && !"TRANSFER".equals(transactionType)) {
            throw new IllegalArgumentException("Invalid transaction type: " + transactionType);
        }

        // Validate 'from' account number
        if (request.getFromAccount() == null || request.getFromAccount() <= 0) {
            throw new IllegalArgumentException("Invalid 'From' account number: " + request.getFromAccount());
        }

        // Validate 'to' account number if it's not null
        if (request.getToAccount() != null && request.getToAccount() <= 0) {
            throw new IllegalArgumentException("Invalid 'To' account number: " + request.getToAccount());
        }

        // Check and validate 'from' account
        Optional<Account> fromAccountOpt = accountRepository.findByAccountNumber(request.getFromAccount());
        if (fromAccountOpt.isEmpty()) {
            throw new IllegalArgumentException("From account not found with account number: " + request.getFromAccount());
        }

        // Check and validate 'to' account if it's not null
        Optional<Account> toAccountOpt = Optional.empty();
        if (request.getToAccount() != null) {
            toAccountOpt = accountRepository.findByAccountNumber(request.getToAccount());
            if (toAccountOpt.isEmpty()) {
                throw new IllegalArgumentException("To account not found with account number: " + request.getToAccount());
            }
        }

        // Process the transaction based on type
        switch (transactionType) {
            case "DEPOSIT":
                if (toAccountOpt.isPresent()) {
                    Account toAccount = toAccountOpt.get();
                    toAccount.setBalance(toAccount.getBalance() + request.getAmount());
                    accountRepository.save(toAccount);
                }
                break;
            case "WITHDRAWAL":
                Account fromAccount = fromAccountOpt.get();
                if (fromAccount.getBalance() < request.getAmount()) {
                    throw new IllegalArgumentException("Insufficient funds in the from account: " + request.getFromAccount());
                }
                fromAccount.setBalance(fromAccount.getBalance() - request.getAmount());
                accountRepository.save(fromAccount);
                break;
            case "TRANSFER":
                if (toAccountOpt.isPresent()) {
                    fromAccount = fromAccountOpt.get();
                    Account toAccount = toAccountOpt.get();
                    if (fromAccount.getBalance() < request.getAmount()) {
                        throw new IllegalArgumentException("Insufficient funds in the from account: " + request.getFromAccount());
                    }
                    fromAccount.setBalance(fromAccount.getBalance() - request.getAmount());
                    toAccount.setBalance(toAccount.getBalance() + request.getAmount());
                    accountRepository.save(fromAccount);
                    accountRepository.save(toAccount);
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid transaction type: " + transactionType);
        }

        // Create and save the transaction
        Transaction transaction = new Transaction();
        transaction.setFromAccountNumber(request.getFromAccount());
        transaction.setToAccountNumber(request.getToAccount());
        transaction.setAmount(request.getAmount());
        transaction.setTransactionType(transactionType);
        transaction.setTimestamp(LocalDateTime.now());

        transaction = transactionRepository.save(transaction);

        return convertToTransactionDTO(transaction);
    }

    private TransactionDTO convertToTransactionDTO(Transaction transaction) {
        return new TransactionDTO(
                transaction.getTransactionId(),
                transaction.getTransactionType(),
                transaction.getFromAccountNumber(),
                transaction.getToAccountNumber(),
                transaction.getAmount(),
                transaction.getTimestamp()
        );
    }

}
