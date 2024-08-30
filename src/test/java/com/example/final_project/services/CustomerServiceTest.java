package com.example.final_project.services;


import com.example.final_project.dto.CustomerDTO;
import com.example.final_project.entities.Account;
import com.example.final_project.entities.Customer;
import com.example.final_project.repository.CustomerRepository;
import com.example.final_project.service.CustomerService;
import com.example.final_project.service.implementations.CustomerServiceImplm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class CustomerServiceTest {

    @TestConfiguration
    static class CustomerServiceConfig {
        @Bean
        public CustomerService customerService() {
            return new CustomerServiceImplm();
        }
    }

    List<Customer> customers = new ArrayList<>();

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void testRegisterCustomer() {
        Customer customer = new Customer("tchico");

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerDTO customer2 = customerService.registerCustomer("tchico");

        assertEquals("tchico",customer2.getFullName());
        assertEquals(1L, customer2.getId());

        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    public void testGetCustomerById() {
        Customer customer = new Customer(1L , "tchico");

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        //Exception handling
        CustomerDTO customer2 = customerService.getCustomerById(1L);

        assertEquals("tchico",customer2.getFullName());

        verify(customerRepository).findById(1L);
    }


    @Test
    public void testDeleteCustomer() {
        Customer customer = new Customer(1L , "tchico");
        Account account1 = new Account(12345678, 1234, "Tiego Savings", "Savings", 700.00, customer);
        Account account2 = new Account(87654321, 1234, "Tiego Checking", "Checking",1000.00,customer);

        customer.setAccounts(Arrays.asList(account1, account2));

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        double totalBalance = customerService.deleteCustomer(1L);

        assertEquals(1700.00,totalBalance);

        verify(customerRepository).findById(1L);
        verify(customerRepository).delete(customer);
    }

    @Test
    public void testFindAllCustomers(){
        List<Integer> accounts = new ArrayList<>();
        Customer customer = new Customer(1L, "tchico");
        List<Customer> customerList = Collections.singletonList(customer);

        when(customerRepository.findAll()).thenReturn(customerList);

        List<CustomerDTO> customers = customerService.findAllCustomers();
        CustomerDTO customerDTO = new CustomerDTO(1L, "tchico",accounts);
        CustomerDTO findCustomerDTO = customers.get(0);

        //one customer tchico should be in Customer list CustomerDTO.
        assertNotNull(customers);
        assertFalse(customers.isEmpty());

        //FAILING THE EXPECTED IS 1 BUT THE ACTUAL IS NULL
        assertEquals(1,customers.size());


        assertEquals(customerDTO.getFullName(), findCustomerDTO.getFullName());
        assertEquals(customerDTO.getId(), findCustomerDTO.getId());
        assertEquals(customerDTO.getAccounts(), findCustomerDTO.getAccounts());

        verify(customerRepository).findAll();


    }

}
