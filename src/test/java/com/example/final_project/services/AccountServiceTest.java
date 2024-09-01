package com.example.final_project.services;

import com.example.final_project.dto.AccountDTO;
import com.example.final_project.dto.CreateAccountRequestDTO;
import com.example.final_project.dto.CreateAccountResponseDTO;
import com.example.final_project.dto.CustomerDTO;
import com.example.final_project.entities.Account;
import com.example.final_project.entities.Customer;
import com.example.final_project.repository.AccountRepository;
import com.example.final_project.repository.CustomerRepository;
import com.example.final_project.service.AccountService;
import com.example.final_project.service.implementations.AccountServiceImplm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class AccountServiceTest {

    List<AccountDTO> accountsDTO = new ArrayList<>();

    private AccountDTO accountDTO;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private CustomerRepository customerRepository;

    private CustomerDTO customerDTO;
    private CreateAccountRequestDTO requestDto;
    private CreateAccountResponseDTO responseDTO;

    @InjectMocks
    private AccountServiceImplm accountServiceImplm;

    private Customer customer;
    private Account account;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        customer = new Customer("Tony Stark");
        customer.setCustomerId(1L); //need customer id because it is in customer class. without this here, will get error
        account = new Account(12345678,1234,"Tony Stark","CHECKING",1000.00,customer);

    }

    @Test
    public void testFindAccountByNumber(){
        Account account = new Account(12345678,1234,"Steve Checking Acct","CHECKING",5000.00, customer);
        when(accountRepository.findByAccountNumber(12345678)).thenReturn(Optional.of(account));

        AccountDTO accountDTO2 = accountServiceImplm.getAccountByNumber(12345678);

        assertNotNull(accountDTO2);
        assertEquals(12345678, accountDTO2.number());
        assertEquals(1234, accountDTO2.sortCode());
        assertEquals("Steve Checking Acct", accountDTO2.name());
        assertEquals(5000.00, accountDTO2.balance());
        assertEquals(customer.getCustomerId(),accountDTO2.customerId());
        verify(accountRepository).findByAccountNumber(12345678);

    }
//    @Test
//    public void testCreateAccount(){
//        Account account = new Account(12345678,1234,"Steve Checking Acct","CHECKING",5000.00, customer);
//        when(customerRepository.save(any())).thenReturn(customer);
//
//        AccountDTO accountDTO2 = accountServiceImplm.createAccount();
//
//        when(customerRepository.save(any(Customer.class))).thenReturn(account.getCustomer());
//
//        assertEquals(12345678 , accountDTO2.number());
////        assertEquals(customer.getCustomerId(),accountDTO2.customerId());
////        verify(customerRepository).save(any(Customer.class));

    }


////    @Test
////    public void testDeleteAccount(){
////        account.getAccountNumber(accountNumber);
////        when(accountRepository.findByAccountNumber()).thenReturn();
////        verify(accountRepository.deleteByAccountNumber.getAccountNumber();
////
////    }
//
//    @Test
//    public void testFindAllAccounts(){
//
//
//    }
//




