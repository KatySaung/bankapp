package com.example.final_project.services;


import com.example.final_project.dto.CustomerDTO;
import com.example.final_project.entities.Customer;
import com.example.final_project.repository.CustomerRepository;
import com.example.final_project.service.CustomerService;
import com.example.final_project.service.CustomerServiceImplm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        customer.setCustomerId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerDTO customer2 = customerService.registerCustomer("tchico");

        assertEquals("tchico",customer2.getFullName());
        assertEquals(1L, customer2.getId());

        verify(customerRepository).save(any(Customer.class));
    }


//    public void testCustomerById throws CustomerByIdNotFoundException() {
//        when(customerRepository.findById(1L).thenReturn(Optional.empty()));
//
//        //Exception handling
//
//        assertEquals(CustomerDTO.getId(),result.getId());
//
//    }


    @Test
    public void testNullAndEmptyParams() {
        //empty username
        Exception emptyUsernameException = assertThrows(IllegalArgumentException.class, () -> {
            new Customer("");
        });
        assertEquals("Username cannot be null or empty", emptyUsernameException.getMessage());
        //null username
        Exception nullUsernameException = assertThrows(IllegalArgumentException.class, () -> {
            new Customer(null);
        });
        assertEquals("Username cannot be null or empty", nullUsernameException.getMessage());
        //empty password
        Exception emptyPasswordException = assertThrows(IllegalArgumentException.class, () -> {
            new Customer("tchico");
        });
    }


//    @Test
//    public void testDeleteCustomer() {
//        customers.add(new Customer("tchico"));
//        when(customerRepository.findById()).thenReturn(java.util.Optional.of(customers.get(0)));
//        verify(customerRepository).deleteById(customers.get(0).getCustomerId());
//        when(customerRepository.findById(any(Long.class))).thenReturn(java.util.Optional.empty());
//        assertThrows(ChangeSetPersister.NotFoundException.class, () -> CustomerService.deleteCustomer(customers.get(0).getCustomerId()));
//    }

}
