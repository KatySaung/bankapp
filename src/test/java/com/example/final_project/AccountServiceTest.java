//package com.example.final_project;
//
//import com.example.final_project.entities.Account;
//import com.example.final_project.repository.AccountRepository;
//import com.example.final_project.service.AccountService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//@DataJpaTest
//public class AccountServiceTest {
//    @Autowired
//    AccountRepository accountRepository;
//    Account accounts;
//    JpaRepository jpaRepository;
//
//
//    @BeforeEach
//    void beforeEach(){
//        Account accounts = new Account();
//        accountRepository.persistAndFlush(accounts);
//    }
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
//
//
//
//}
