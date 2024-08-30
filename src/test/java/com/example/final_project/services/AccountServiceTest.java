package com.example.final_project.services;

import com.example.final_project.dto.AccountDTO;
<<<<<<< HEAD
import com.example.final_project.dto.CreateAccountResponseDto;
import com.example.final_project.dto.CustomerDTO;
import com.example.final_project.entities.Account;
import com.example.final_project.entities.Customer;
import com.example.final_project.repository.AccountRepository;
import com.example.final_project.service.AccountServiceImplm;
=======
import com.example.final_project.entities.Account;
import com.example.final_project.entities.Customer;
import com.example.final_project.repository.AccountRepository;
import com.example.final_project.service.implementations.AccountServiceImplm;
>>>>>>> 766fa48578cdbb34ff436900fa4eed3fb766e73e
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
<<<<<<< HEAD
=======
<<<<<<< HEAD

=======
>>>>>>> 766fa48578cdbb34ff436900fa4eed3fb766e73e
>>>>>>> final-project
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class AccountServiceTest {


    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImplm accountServiceImplm;

    private Customer customer;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        customer = new Customer("Tony Stark");
        customer.setCustomerId(1L); //need customer id because it is in customer class. without this here, will get error

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
    @Test
<<<<<<< HEAD
    public void testCreateCAccount(){
        when(accountRepository.save(any(Account.class))).thenReturn(accountDTO);
        AccountDTO accountDTO2 = accountServiceImplm.getAccountByNumber(12345678);

        when(customerRepository.save(any(Customer.class))).thenReturn(new Customer("kathe"));
=======
    public void testCreateAccount(){
        Account account = new Account(12345678,1234,"Snowball's Checking Acct","CHECKING",5000.00, customer);
        when(accountRepository.save(account)).thenReturn(account);

        AccountDTO accountDTO2 = accountServiceImplm.getAccountByNumber(12345678);

        when(customerRepository.save(any(Customer.class))).thenReturn(new Customer("Snowball"));

>>>>>>> final-project
        assertEquals(customerDTO.getFullName(), "kathe");
        verify(customerRepository).save(any(Customer.class));
    }

//    @Test
      //no method to updateAccount
//    public void testUpdateAccount(){
//
//    }

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
