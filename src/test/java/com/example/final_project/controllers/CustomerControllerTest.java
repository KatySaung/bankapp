package com.example.final_project.controllers;


import com.example.final_project.controller.CustomerController;
import com.example.final_project.dto.CustomerDTO;
import com.example.final_project.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;



@ExtendWith(MockitoExtension.class)

public class CustomerControllerTest {


    @Autowired
    @Mock
    private CustomerService customerService;
    @InjectMocks
    private CustomerController customerController;




    @Test
    public void testGetCustomerById_Success() {
        Long customerId = 1L;
        List<Integer> account = new ArrayList<>();
        CustomerDTO customerDTO = new CustomerDTO(1L,"mad max", account);
        when(customerService.getCustomerById(customerId)).thenReturn(customerDTO);
        ResponseEntity<CustomerDTO> response = customerController.getCustomerById(customerId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customerDTO, response.getBody());

    }

    @Test
    public void testGetCustomerById_NotFound() {
        Long customerId = 1L;
        when(customerService.getCustomerById(customerId)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        ResponseEntity<CustomerDTO> response = customerController.getCustomerById(customerId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testRegisterCustomer_Success() {
        String fullName = "John Doe";
        Long customerId = 1l;
        List<Integer> account = new ArrayList<>();
        CustomerDTO customerDTO = new CustomerDTO(customerId,fullName, account);
        customerDTO.setFullName(fullName);
        when(customerService.registerCustomer(fullName)).thenReturn(customerDTO);
        ResponseEntity<CustomerDTO> response = customerController.registerCustomer(fullName);
        assertEquals(HttpStatus.CREATED, response.getStatusCode()); assertEquals(customerDTO, response.getBody()); }

    @Test
    public void testDeleteCustomer_Success() {
        Long customerId = 1L;
        double totalFunds = 100.0; when(customerService.deleteCustomer(customerId)).thenReturn(totalFunds);
        ResponseEntity<Double> response = customerController.deleteCustomer(customerId);
        assertEquals(HttpStatus.OK, response.getStatusCode()); assertEquals(totalFunds, response.getBody()); }

    @Test
    public void testDeleteCustomer_NotFound() {

        Long customerId = 1L;
        when(customerService.deleteCustomer(customerId)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        ResponseEntity<Double> response = customerController.deleteCustomer(customerId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testFullCustomerExistence(){
        Long customerId = 1L;
        List<Integer> account = new ArrayList<>();
        CustomerDTO customerDTO = new CustomerDTO(1L,"mad max", account);
        when(customerService.getCustomerById(customerId)).thenReturn(customerDTO);
        ResponseEntity<CustomerDTO> response = customerController.getCustomerById(customerId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("mad max", customerDTO.getFullName());
        assertEquals(1L, customerDTO.getId());
        assertEquals(account, customerDTO.getAccounts());



    }


}

