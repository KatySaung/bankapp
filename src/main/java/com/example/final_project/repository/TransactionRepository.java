package com.example.final_project.repository;

import java.time.LocalDateTime;
import com.example.final_project.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByTransactionType(String transactionType);
    List<Transaction> findByTimestampBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Transaction> findByFromAccountNumber(Long fromAccountNumber);
}
