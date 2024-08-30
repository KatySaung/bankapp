//package com.example.final_project.controllers;
//
//import com.example.final_project.entities.Account;
//import com.example.final_project.repository.AccountRepository;
//import org.aspectj.lang.annotation.Before;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MockMvcBuilder;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//public class AccountControllerTest {
//
//    @Autowired
//    private MockMvc mvcMock;
//
//    @Mock
//    private AccountRepository accountRepository;
//
//    @BeforeEach
//    void setup() {
//        when(accountRepository.findById(1L)).thenReturn(Optional.of(new Account(1, 100.00)));
//    }
//
//    @Test
//    public void TestGetAccount() {
//        RequestBuilder builder = MockMvcRequestBuilders
//                .get("/accounts/1");
//        try {
//            MvcResult mvcResult = mvcMock.perform(builder).andExpect(status().isOk()).andReturn();
//            String json = "{....}";
//            assertEquals(json, mvcResult.getResponse().getContentAsString());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}


package com.example.final_project.controllers;

import com.example.final_project.controller.CustomerController;
import com.example.final_project.dto.*;
import com.example.final_project.entities.Account;
import com.example.final_project.service.AccountService;
import com.example.final_project.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;


    private CreateAccountResponseDTO responseDTO;
    private AccountDTO account;
    private CreateAccountRequestDTO requestDTO;
    @BeforeEach
    void setup() {
        List<TransactionDTO> transactions = new ArrayList<>();
        account = new AccountDTO(1,1234,"checking",100.00,transactions,200.00,1L);
        responseDTO = new CreateAccountResponseDTO(1,1234,"checking",100.00,1L);
        requestDTO = new CreateAccountRequestDTO();

    }

    @Test
    public void testGetAccountByNumber() {
        try {
            when(accountService.getAccountByNumber(1)).thenReturn(account);

            mockMvc.perform(get("/account/1"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.number").value(1))
                    .andExpect(jsonPath("$.name").value("checking"));
        } catch (Exception e) {
            // Handle exceptions (e.g., print stack trace, fail the test)
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAccountByNumberNotFound() {
        try {
            when(accountService.getAccountByNumber(2)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));
            mockMvc.perform(get("/account/2"))
                    .andExpect(status().isNotFound());
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateAccount() {
        try {
            when(accountService.createAccount(any())).thenReturn(responseDTO);

            mockMvc.perform(post("/account")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(requestDTO)))
                            .andExpect(status().isCreated())
                            .andExpect(jsonPath("$.accountNumber").value(1))
                            .andExpect(jsonPath("$.sortCode").value(1234))
                            .andExpect(jsonPath("$.accountName").value("checking"))
                            .andExpect(jsonPath("$.balance").value(100.00))
                            .andExpect(jsonPath("$.customerId").value(1L));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteCustomer() {
        try {
            double balance = 100.00;
            when(customerService.deleteCustomer(1L)).thenReturn(balance);

            mockMvc.perform(delete("/customer/1"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(String.valueOf(balance)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
