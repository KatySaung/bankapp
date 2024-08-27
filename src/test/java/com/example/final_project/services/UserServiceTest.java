package com.example.final_project.services;


import com.example.final_project.entities.Customer;
import com.example.final_project.repository.CustomerRepository;
import com.example.final_project.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    List<Customer> customers = new ArrayList<>();

    @Mock
    private CustomerRepository userRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser() {
        customers.add(new Customer("tchico"));
        when(userRepository.save(any(Customer.class))).thenReturn(customers.get(0));
        Customer customer = new Customer("tchico");
        Customer savedUser = CustomerService.registerCustomer(customer);
    }

    @Test
    public void testNullAndEmptyParams() {
        //empty username
        Exception emptyUsernameException = assertThrows(IllegalArgumentException.class, () -> {
            new User("", "tchicopassword");
        });
        assertEquals("Username cannot be null or empty", emptyUsernameException.getMessage());
        //null username
        Exception nullUsernameException = assertThrows(IllegalArgumentException.class, () -> {
            new User(null, "tchicopassword");
        });
        assertEquals("Username cannot be null or empty", nullUsernameException.getMessage());
        //empty password
        Exception emptyPasswordException = assertThrows(IllegalArgumentException.class, () -> {
            new User("tchico", "");
        });
        assertEquals("Password cannot be null or empty", emptyPasswordException.getMessage());
        //null password
        Exception nullPasswordException = assertThrows(IllegalArgumentException.class, () -> {
            new User("tchico", null);
        });
        assertEquals("Password cannot be null or empty", nullPasswordException.getMessage());
    }

    @Test
    public void testLoginSuccess() {
        users.add(new User("tchico", "tchicopassword"));
        when(userRepository.findByUsername(any(String.class))).thenReturn(users.get(0));
        User user = userService.login("tchico", "tchicopassword");
        assertEquals(users.get(0), user);
    }

    @Test
    public void testLoginFailure() {
        when(userRepository.findByUsername(any(String.class))).thenReturn(null);
        User user = userService.login("tchico", "wrongPassword");
        assertEquals(null, user);
    }


    @Test
    public void testDeleteUser() {
        users.add(new User("tchico", "tchicopassword"));
        when(userRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(users.get(0)));
        userService.deleteUser(users.get(0).getUserId());
        verify(userRepository).deleteById(users.get(0).getUserId());
        when(userRepository.findById(any(Long.class))).thenReturn(java.util.Optional.empty());
        assertThrows(ChangeSetPersister.NotFoundException.class, () -> userService.deleteUser(users.get(0).getUserId()));
    }


}
