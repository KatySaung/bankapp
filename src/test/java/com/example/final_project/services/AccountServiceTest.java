package com.example.final_project.services;

import com.example.final_project.dto.AccountDTO;
import com.example.final_project.dto.CustomerDTO;
import com.example.final_project.dto.RegisterCustomerDto;
import com.example.final_project.entities.Account;
import com.example.final_project.entities.Customer;
import com.example.final_project.repository.AccountRepository;
import com.example.final_project.repository.CustomerRepository;
import com.example.final_project.service.AccountService;
import com.example.final_project.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.security.auth.login.AccountNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;
    private AccountService accountService;

    private Account account;
    private AccountDTO accountDTO;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        account = new Account(12345678,1234,"CK Account", "Checking",1000.00,new Customer("CK"));
        accountDTO = new AccountDTO(12345678,1234,"Kat",500.00,null,200.00,1L);
    }

    //
    @Test
    public void testFindAccountByNumber(){

    }
    @Test
    public void testCreateCustomer(){
//        when(accountRepository.save(account)).thenReturn(account.);
//        customerDTO = customerService.registerCustomer("kathe");
//        when(customerRepository.save(any(Customer.class))).thenReturn(new Customer("kathe"));
//        assertEquals(customerDTO.getFullName(), "kathe");
//        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    public void testUpdateAccount(){

    }

//    @Test
//    public void testDeleteAccount(){
//        account.getAccountNumber(accountNumber);
//        when(accountRepository.findByAccountNumber()).thenReturn();
//        verify(accountRepository.deleteByAccountNumber.getAccountNumber();
//
//    }

    @Test
    public void testFindAllAccounts(){


    }




}
