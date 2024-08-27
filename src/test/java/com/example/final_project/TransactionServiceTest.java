package com.example.final_project;

import com.example.final_project.dto.TransactionDTO;
import com.example.final_project.dto.TransactionRequestDTO;
import com.example.final_project.entities.Transaction;
import com.example.final_project.repository.TransactionRepository;
import com.example.final_project.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;


public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;
    private Transaction transaction;
    private TransactionDTO transactionDTO;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        TransactionDTO transactionDTO_one = new TransactionDTO();
        // transaction = new Transaction(1L,"WITHDRAWAL",987654321,1234567890, 100.00, LocalDateTime.now());
        transactionDTO = new TransactionDTO(1L,"DEPOSIT",987654321,1234567890,500.00, LocalDateTime.now());
    }
//
//    @Test
//    public void testProcessTransaction() throws TransactionNotFoundException{
//        TransactionRequestDTO TransactionRequestDTO = null;
//        when(transactionService.processTransaction(TransactionRequestDTO).thenReturn(TransactionRequestDTO));
//        TransactionRequestDTO result = transactionService.processTransaction(TransactionRequestDTO);
//        //
//    }
//
//    @Test


}
