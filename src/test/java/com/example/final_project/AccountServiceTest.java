package com.example.final_project;

import com.example.final_project.dto.AccountDTO;
import com.example.final_project.entities.Account;
import com.example.final_project.entities.Customer;
import com.example.final_project.repository.AccountRepository;
import com.example.final_project.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;
    private Account account;
    private AccountDTO accountDTO;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        account = new Account();
        accountDTO = new AccountDTO(12345678,1234,"Kat",, 500.00,1000.00);


    }



}
