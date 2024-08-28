package com.example.final_project.services;


import com.example.final_project.dto.CustomerDTO;
import com.example.final_project.entities.Customer;
import com.example.final_project.repository.CustomerRepository;
import com.example.final_project.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    List<Customer> customers = new ArrayList<>();

    @Mock
    private CustomerRepository customerRepository;

//    @InjectMocks
//    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

    }

//    @Test
//    public void testRegisterCustomer() {
//        customers.add(new Customer("tchico"));
//        when(customerRepository.save(any(Customer.class))).thenReturn(customers.get(0));
//        Customer customer = new Customer("tchico");
//        Customer savedCustomer = customerService.registerCustomer(customers);
//    }


//    @Test
//    public void testRegisterCustomerSuccess() {
//        customers.add(new Customer("tchico"));
//        when(customerRepository.findByFullName(any(String.class).toString())).thenReturn(customers.get(0));
//        Customer customer = CustomerService.registerCustomer("tchico");
//        assertEquals(customers.get(0), customer);
//    }

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
