package com.example.final_project.repository;

import com.example.final_project.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByFromAccountId(Long accountId);
     public List<Transaction> findByAccountId(Long accountId);
     public List<Transaction> findByUserId(Long userId);
     public List<Transaction> findByTransactionType(String transactionType);
    List<Transaction> findByTimestampBetween(String startDate, String endDate);
}
