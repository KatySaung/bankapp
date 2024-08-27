package com.example.final_project;

import com.example.final_project.dto.CustomerDTO;
import com.example.final_project.dto.TransactionDTO;
import com.example.final_project.entities.Account;
import com.example.final_project.entities.Customer;
import com.example.final_project.entities.Transaction;
import com.example.final_project.repository.CustomerRepository;
import com.example.final_project.repository.TransactionRepository;
import com.example.final_project.service.CustomerService;
import com.example.final_project.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class CustomerControllerTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;
    private Customer customer;
    private CustomerDTO customerDTO;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        List<Integer> account = new ArrayList<>();
        account.add(1234);
        account.add(5678);
        int id = 123456;
        Long idk = (long) id;

        CustomerDTO customer = new CustomerDTO(idk,"mad max", account);


    }

}
