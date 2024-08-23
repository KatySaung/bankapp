package com.example.final_project.repository;

import com.example.final_project.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // public List<Transaction> findByAccountId(Long accountId);
    // public List<Transaction> findByUserId(Long userId);
    // public List<Transaction> findByTimestampBetween(LocalDateTime startDate, LocalDateTime endDate);
    // public List<Transaction> findByTransactionType(String transactionType);
}
