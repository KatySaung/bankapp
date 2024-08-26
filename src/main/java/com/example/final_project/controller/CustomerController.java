package com.example.final_project.controller;

import com.example.final_project.dto.RegisterustomerDto;
import com.example.final_project.dto.CustomersDTO;
import com.example.final_project.entities.Customers;
import com.example.final_project.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customersService;

    // Retrieve all customers
    @GetMapping
    public ResponseEntity<List<CustomersDTO>> getAllCustomers() {
        List<CustomersDTO> customers = customersService.findAllCustomers();
        return ResponseEntity.ok(customers);
    }

    // Retrieve a customers by customers ID
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long customersId) {
        CustomerDTO customers = customersService.getCustomerById(customersId);
        return ResponseEntity.ok(customers);
    }

    @PostMapping
    public ResponseEntity<Customer> register(@RequestBody RegisterCustomerDto customers) {
        Customer newCustomer = customersService.registerDTO(customers.getName());
        return ResponseEntity.status(201).body(newCustomer);
    }

    // Delete a customers by customers ID
    @DeleteMapping("/{customersId}")
    public ResponseEntity<Double> deleteCustomer(@PathVariable Long CustomerId) {
        double totalFunds = customersService.deletecustomers(customersId);
        return ResponseEntity.ok(totalFunds);
    }
}
