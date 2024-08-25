package com.example.final_project.service;

import com.example.final_project.dto.TransactionDTO;
import com.example.final_project.entities.Account;
import com.example.final_project.entities.Transaction;
import com.example.final_project.repository.AccountRepository;
import com.example.final_project.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        Optional<Account> fromAccount = accountRepository.findByAccountNumber(transactionDTO.getFromAccountNumber());
        if (!fromAccount.isPresent()) {
            throw new RuntimeException("From account not found");
        }

        Optional<Account> toAccount = Optional.empty();
        if (transactionDTO.getToAccountNumber() != null) {
            toAccount = accountRepository.findByAccountNumber(transactionDTO.getToAccountNumber());
            if (!toAccount.isPresent()) {
                throw new RuntimeException("To account not found");
            }
        }

        Transaction transaction = new Transaction();
        transaction.setFromAccountNumber(transactionDTO.getFromAccountNumber());
        transaction.setToAccountNumber(transactionDTO.getToAccountNumber());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setTransactionType(transactionDTO.getTransactionType());
        transaction.setTimestamp(transactionDTO.getTimestamp());

        transactionRepository.save(transaction);

        return new TransactionDTO(
                transaction.getId(),
                transaction.getFromAccountNumber(),
                transaction.getToAccountNumber(),
                transaction.getAmount(),
                transaction.getTransactionType(),
                transaction.getTimestamp()
        );
    }
}
