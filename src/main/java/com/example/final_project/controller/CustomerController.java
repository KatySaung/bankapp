package com.example.final_project.controller;

import com.example.final_project.dto.RegisterCustomerDto;
import com.example.final_project.dto.CustomerDTO;
import com.example.final_project.entities.Customer;
import com.example.final_project.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService CustomerService;

    // Retrieve all Customers
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> Customers = CustomerService.findAllCustomers();
        return ResponseEntity.ok(Customers);
    }

    // Retrieve a Customer by Customer ID
    @GetMapping("/{CustomerId}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long CustomerId) {
        CustomerDTO Customer = CustomerService.getCustomerById(CustomerId);
        return ResponseEntity.ok(Customer);
    }

    @PostMapping
    public ResponseEntity<Customer> register(@RequestBody RegisterCustomerDto Customer) {
        Customer newCustomer = CustomerService.registerDTO(Customer.getName());
        return ResponseEntity.status(201).body(newCustomer);
    }

    // Delete a Customer by Customer ID
    @DeleteMapping("/{CustomerId}")
    public ResponseEntity<Double> deleteCustomer(@PathVariable Long CustomerId) {
        double totalFunds = CustomerService.deleteCustomer(CustomerId);
        return ResponseEntity.ok(totalFunds);
    }
}
