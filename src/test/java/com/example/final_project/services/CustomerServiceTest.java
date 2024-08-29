package com.example.final_project.services;


import com.example.final_project.dto.CustomerDTO;
import com.example.final_project.entities.Customer;
import com.example.final_project.repository.CustomerRepository;
import com.example.final_project.service.CustomerService;
import com.example.final_project.service.CustomerServiceImplm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
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
    public void testCustomerById() {
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

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        CustomerDTO customer2 = customerService.getCustomerById(1L);

        assertEquals("","");

        verify(customerRepository).findById(1L);
    }

    @Test
    public void testFindAllCustomers(){
        List<Integer> accounts = new ArrayList<>();

        CustomerDTO customerDTO = new CustomerDTO(1L , "tchico", accounts);
        when(customerService.findAllCustomers().thenReturn(customerDTO));

        assertNotNull(customerDTO, "AccountDTO should not be null");
        assertEquals("tchico", customerDTO.getFullName());
        assertEquals(1L, customerDTO.getId());
        assertEquals(accounts, customerDTO.getAccounts());


    }

}
