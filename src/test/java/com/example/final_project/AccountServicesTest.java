package com.example.final_project;

import com.example.final_project.entities.Account;
import com.example.final_project.repository.AccountRepository;
import com.example.final_project.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.example.final_project.entities.Account;
import com.example.final_project.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class AccountServicesTest {
//
//    @Test
//    public void testCreateAccount(){
//        Account account = new Account();
//        Account account1 = new Account();
//        assertEquals(account, account1);
//    }
//
//    @Test
//    public void testCreateAccountService(){
//        AccountService account = new AccountService();
//        account.createAccount(1.00,"idk",1);
//        AccountService account1 = new AccountService();
//        account1.createAccount(1.00,"idk",1);
//        assertEquals(account, account1);
//    }




   public class AccountServicesTest {

        @Mock
        private AccountRepository accountRepository;

        @InjectMocks
        private AccountService accountService;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        public void testCreateAccount_Successful() {

            double balance = 1000.0;
            String accountType = "Savings";
            int expectedAccountNumber = 1; // Since AtomicInteger starts at 1

            Account mockAccount = new Account();
            mockAccount.setAccountNumber(expectedAccountNumber);
            mockAccount.setBalance(balance);
            mockAccount.setAccountType(accountType);
            when(accountRepository.save(any(Account.class))).thenReturn(mockAccount);


            Account createdAccount = accountService.createAccount(balance, accountType, 0); // 0 is ignored

            // Assert
            assertNotNull(createdAccount);
            assertEquals(expectedAccountNumber, createdAccount.getAccountNumber());
            assertEquals(balance, createdAccount.getBalance());
            assertEquals(accountType, createdAccount.getAccountType());

            verify(accountRepository, times(1)).save(any(Account.class));
        }

        @Test
        public void testGenreateUniqueId(){
            assertEquals(1, accountService.generateUniqueId());
            
        }

        @Test
        public void testCreateAccount_NegativeBalance() {
            // Arrange
            double balance = -500.0;
            String accountType = "Checking";

            // Act & Assert
            assertThrows(IllegalArgumentException.class, () ->
                    accountService.createAccount(balance, accountType, 0));

            verify(accountRepository, never()).save(any(Account.class));
        }

        // Additional tests can be added to cover other scenarios:
        // - Account number already exists (requires mocking accountRepository.existsByAccountNumber)
        // - ...

    }

