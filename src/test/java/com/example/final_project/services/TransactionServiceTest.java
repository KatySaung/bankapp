package com.example.final_project.services;

import com.example.final_project.dto.TransactionDTO;
import com.example.final_project.dto.TransactionRequestDTO;
import com.example.final_project.entities.Account;
import com.example.final_project.entities.Customer;
import com.example.final_project.entities.Transaction;
import com.example.final_project.repository.AccountRepository;
import com.example.final_project.repository.TransactionRepository;
import com.example.final_project.service.implementations.TransactionServiceImplm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private AccountRepository accountRepository;
    private TransactionServiceImplm transactionService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        transactionService = new TransactionServiceImplm(transactionRepository, accountRepository);

        //After entity (transaction object is saved) assign it to an Id of 1L. This is to avoid nullpointerexception because this method normally returns null by default.
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> {
            Transaction transaction = invocation.getArgument(0);
            transaction.setTransactionId(1L);
            return transaction;
        });
    }


    @Test
    public void testProcessDepositTransaction(){
        Customer customer1 = new Customer("CK Boss");
        Account toAccount = new Account(87654321,1234,"CK Checking Account","CHECKING",1000.00, customer1);

        TransactionRequestDTO transactionrequestDTO = new TransactionRequestDTO(2,87654321,1234,500.00);

        when(accountRepository.findByAccountNumber(87654321)).thenReturn(Optional.of(toAccount));

        TransactionDTO result = transactionService.processTransaction(transactionrequestDTO);

        assertNotNull(result);
        assertEquals(1500.00, toAccount.getBalance());
        verify(accountRepository).save(toAccount);
        verify(transactionRepository).save(any(Transaction.class));

    }

    @Test
    public void testProcessWithdrawalTransaction(){
        Customer customer1 = new Customer("CK Boss");
        Account fromAccount = new Account(12345678,null,"CK Savings Account","SAVINGS",5000.00, customer1);

        TransactionRequestDTO transactionrequestDTO = new TransactionRequestDTO(1,12345678,null,null, null,4000.00);
        when(accountRepository.findByAccountNumber(12345678)).thenReturn(Optional.of(fromAccount));
        //Transaction savedTransaction = transactionService.processTransaction(TransactionRequestDTO);

        TransactionDTO result = transactionService.processTransaction(transactionrequestDTO);

        assertNotNull(result);
        assertEquals(1000, fromAccount.getBalance());
        verify(accountRepository).save(fromAccount);
        verify(transactionRepository).save(any(Transaction.class));

    }
    @Test
    public void testProcessTransferTransaction(){
        Customer customer1 = new Customer("CK Boss");
        Account toAccount = new Account(87654321,1234,"CK Checking Account","CHECKING",1000.00, customer1);
        Account fromAccount = new Account(12345678,1234,"CK Savings Account","SAVINGS",1000.00, customer1);
        TransactionRequestDTO transactionrequestDTO = new TransactionRequestDTO(0,12345678,1234,87654321, 1234,500.00);

        when(accountRepository.findByAccountNumber(87654321)).thenReturn(Optional.of(toAccount));
        when(accountRepository.findByAccountNumber(12345678)).thenReturn(Optional.of(fromAccount));

        TransactionDTO result = transactionService.processTransaction(transactionrequestDTO);

        assertNotNull(result);
        assertEquals(1500.00, toAccount.getBalance());
        assertEquals(500.00, fromAccount.getBalance());
        verify(accountRepository).save(toAccount);
        verify(accountRepository).save(fromAccount);
        verify(transactionRepository).save(any(Transaction.class));

    }

}
