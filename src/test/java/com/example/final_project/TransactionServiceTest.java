package com.example.final_project;

import com.example.final_project.dto.TransactionDTO;
import com.example.final_project.entities.Transaction;
import com.example.final_project.repository.TransactionRepository;
import com.example.final_project.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;


public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this)
    }

    @Test
    public void testCreateTransaction(){
        TransactionDTO transactionDTO = new TransactionDTO(null,987654321,1234567890,100.00,"Deposit", LocalDateTime.now());
        Transaction transaction = new Transaction();
        transaction.setTransactionId(1L);
        transaction.setFromAccountNumber(987654321);
        transaction.setToAccountNumber(1234567890);
        transaction.setAmount(500.00);
        transaction.setTransactionType("DEPOSIT");
        transaction.setTimestamp(transactionDTO.getTimestamp());


    }

}
