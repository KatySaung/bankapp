package com.example.final_project.services;

import com.example.final_project.dto.AccountDTO;
import com.example.final_project.entities.Account;
import com.example.final_project.entities.Customer;
import com.example.final_project.repository.AccountRepository;
import com.example.final_project.service.AccountService;
import com.example.final_project.service.AccountServiceImplm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private AccountServiceImplm accountService;

    private Customer customer;
    private Account account;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        Customer customer = new Customer("Tony Stark");
        customer.setCustomerId(1L); //need customer id because it is in customer class. without this here, will get error
        Account account = new Account(12345678,1234,"Tony Stark's Account", "Checking",1000.00, customer);
//        System.out.println("Customer ID: " + customer.getCustomerId());

//        accountService = new AccountServiceImplm(accountRepository, null);
    }


    @Test
    //Failed Test, RunTime Exception from AccountServiceImplmn line 29. Add null conditional for testing in line 32-34.
    public void testFindAccountByNumber(){
        Account account = new Account();
        when(accountRepository.findByAccountNumber(12345678)).thenReturn(Optional.of(account));

        AccountDTO accountDTO = accountService.getAccountByNumber(12345678);


        assertNotNull(accountDTO, "AccountDTO should not be null");
        assertEquals(12345678, accountDTO.number());
        assertEquals(1234, accountDTO.sortCode());
        assertEquals("Tony Stark's Account", accountDTO.name());
        assertEquals(1000.00, accountDTO.balance());
        assertEquals(customer.getCustomerId(),accountDTO.customerId());
        verify(accountRepository).findByAccountNumber(12345678);


    }
//    @Test
//    public void testCreateCustomer(){
////        when(accountRepository.save(account)).thenReturn(account.);
////        customerDTO = customerService.registerCustomer("kathe");
////        when(customerRepository.save(any(Customer.class))).thenReturn(new Customer("kathe"));
////        assertEquals(customerDTO.getFullName(), "kathe");
////        verify(customerRepository).save(any(Customer.class));
//    }
//
//    @Test
//    public void testUpdateAccount(){
//
//    }
//
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



}
