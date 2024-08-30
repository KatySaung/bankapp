package com.example.final_project.services;

import com.example.final_project.dto.AccountDTO;
import com.example.final_project.dto.CustomerDTO;
import com.example.final_project.entities.Account;
import com.example.final_project.entities.Customer;
import com.example.final_project.repository.AccountRepository;
import com.example.final_project.service.AccountService;
import com.example.final_project.service.AccountServiceImplm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class AccountServiceTest {

    List<AccountDTO> accountDTO = new ArrayList<>();

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
//        Account account = new Account(12345678,1234,"Tony Stark's Account", "Checking",1000.00, customer);
//        System.out.println("Customer ID: " + customer.getCustomerId());

    }

    @Test
    //Failed Test, RunTime Exception from AccountServiceImplmn line 29. Add null conditional for testing in line 32-34.
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
//    public void testCreateCAccount(){
//        when(accountRepository.save(account)).thenReturn(account.);
//        customerDTO = customerService.registerCustomer("kathe");
//        when(customerRepository.save(any(Customer.class))).thenReturn(new Customer("kathe"));
//        assertEquals(customerDTO.getFullName(), "kathe");
//        verify(customerRepository).save(any(Customer.class));
//    }

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
