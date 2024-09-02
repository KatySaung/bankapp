package com.example.final_project.services;

import com.example.final_project.dto.AccountDTO;
import com.example.final_project.dto.CreateAccountRequestDTO;
import com.example.final_project.dto.CreateAccountResponseDTO;
import com.example.final_project.dto.CustomerDTO;
import com.example.final_project.entities.Account;
import com.example.final_project.entities.Customer;
import com.example.final_project.repository.AccountRepository;
import com.example.final_project.repository.CustomerRepository;
import com.example.final_project.service.AccountService;
import com.example.final_project.service.implementations.AccountServiceImplm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class AccountServiceTest {

    List<AccountDTO> accountsDTO = new ArrayList<>();

    private AccountDTO accountDTO;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private AccountServiceImplm accountServiceImplm;

    private Customer customer;
    private Customer customer2;
    private CustomerDTO customerDTO;
    private Account account;
    private Account account2;
    private CreateAccountRequestDTO requestDto;
    private CreateAccountResponseDTO responseDTO;

    private AccountDTO accountDTO1;
    private AccountDTO accountDTO2;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        customer = new Customer("Tony Stark");
        customer.setCustomerId(1L); //need customer id because it is in customer class. without this here, will get error
        account = new Account(12345678, 1234, "Tony Stark", "CHECKING", 1000.00, customer);


        customer2 = new Customer("Pepper Potts");
        customer2.setCustomerId(2L); //need customer id because it is in customer class. without this here, will get error
        account2 = new Account(87654321, 4321, "Pepper Potts", "SAVINGS", 2000.00, customer2);

        requestDto = new CreateAccountRequestDTO(1L,"Tony Stark", 1000.00);
        responseDTO = new CreateAccountResponseDTO(12345678,1234,"Tony Stark",1000.00,1L);

        accountDTO1 = new AccountDTO(12345678,1234, "Tony Stark",1000.00, new ArrayList<>(), 1000.00, 1L);
        accountDTO2 = new AccountDTO(87654321,4321, "Pepper Potts",2000.00, new ArrayList<>(), 2000.00, 2L);


    }

    @Test
    public void testFindAccountByNumber() {
//        Account account = new Account(12345678, 1234, "Steve Checking Acct", "CHECKING", 5000.00, customer);
        when(accountRepository.findByAccountNumber(12345678)).thenReturn(Optional.of(account));

        AccountDTO accountDTO2 = accountServiceImplm.getAccountByNumber(12345678);

        assertNotNull(accountDTO2);
        assertEquals(12345678, accountDTO2.number());
        assertEquals(1234, accountDTO2.sortCode());
        assertEquals("Tony Stark", accountDTO2.name());
        assertEquals(1000.00, accountDTO2.balance());
        assertEquals(customer.getCustomerId(), accountDTO2.customerId());
        verify(accountRepository).findByAccountNumber(12345678);

    }

    @Test
    public void testCreateAccount() {
        //accountResponseDTO variable is for the actual result in the testCreateAccount() method
        //responseDTO from setUp() method will still be for the expected result

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        //need to create constructor with variable account1 with hard coded value because accountResponseDTO will generate a new account number each time.
        Account account1 = new Account(12345678, 1234,"Tony Stark","CHECKING", 1000.00, customer);
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        CreateAccountResponseDTO accountResponseDTO = accountServiceImplm.createAccount(requestDto);

        assertNotNull(accountResponseDTO);
        assertEquals(responseDTO.getAccountNumber(), account1.getAccountNumber()); //cannot use accountResponseDTO here
        assertEquals(responseDTO.getSortCode(), accountResponseDTO.getSortCode());
        assertEquals(responseDTO.getAccountName(), accountResponseDTO.getAccountName());
        assertEquals(responseDTO.getBalance(), accountResponseDTO.getBalance());
        assertEquals(responseDTO.getCustomerId(), accountResponseDTO.getCustomerId());

        verify(customerRepository).findById(1L);
        verify(accountRepository).save(any(Account.class));

    }


    @Test
    public void testDeleteAccount(){
        when(accountRepository.findByAccountNumber(account.getAccountNumber())).thenReturn(Optional.of(account));
        //mockito method doNothing() used here to mock this delete method because will not return a value, so it's a void method
        doNothing().when(accountRepository).delete(account);

        //create variable currentBalance to verify method returns a current balance after account is deleted
        double currentBalance = accountServiceImplm.deleteAccount(account.getAccountNumber());

        //verify account balance matches currentBalance
        assertEquals(account.getBalance(), currentBalance);

        verify(accountRepository).findByAccountNumber(account.getAccountNumber());
        verify(accountRepository).delete(account);

    }

    @Test
    public void testFindAllAccounts(){
        //list for expected AccountDTOs
        List<AccountDTO> expectedAccountDTOS =List.of(accountDTO1,accountDTO2);

        when(accountRepository.findAll()).thenReturn(List.of(account, account2));

        //list for actual AccountDTOs
        List<AccountDTO> actualAccountDTOs = accountServiceImplm.getAllAccounts();

        assertNotNull(actualAccountDTOs);
        assertEquals(expectedAccountDTOS.size(), actualAccountDTOs.size());

        //compare both DTO lists to make sure the accounts match the actual DTO
        for (int i = 0; i < expectedAccountDTOS.size(); i++){
            assertEquals(expectedAccountDTOS.get(i), actualAccountDTOs.get(i));
        }

        verify(accountRepository).findAll();


    }

}




