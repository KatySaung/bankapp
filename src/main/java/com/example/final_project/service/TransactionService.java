package com.example.final_project.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.example.final_project.entities.Transaction;
import com.example.final_project.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> createTransaction(Transaction transaction) {
        return transactionRepository.saveAll(List.of(transaction));
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(Transaction updatedTransaction) {
        return transactionRepository.save(updatedTransaction);
    }

    public void deleteTransaction(Long accountNumber) {
        //transactionRepository.deleteByAccountNumber(accountNumber);
    }

    public List<Transaction> getTransactionsByAccountNumber(Long accountNumber) {
        return transactionRepository.findByFromAccountNumber(accountNumber);
    }

    public List<Transaction> getTransactionsByDate(LocalDateTime startDate, LocalDateTime endDate) {
        return transactionRepository.findByTimestampBetween(startDate, endDate);
    }

    public List<Transaction> getTransactionsByUserId(Long userId) {
        return null;
        //return transactionRepository.findByUserId(userId);

    }

    public List<Transaction> getTransactionsByType(String transactionType) {
        return transactionRepository.findByTransactionType(transactionType);
    }

    public List<Transaction> findByAccountNumber(Long accountNumber) {
        return null;
        //return transactionRepository.findByAccountNumber(accountNumber);
    }
}
