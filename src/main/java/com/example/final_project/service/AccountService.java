package com.example.final_project.service;

import com.example.final_project.entities.Account;
import com.example.final_project.entities.User;
import com.example.final_project.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    private AtomicInteger nextId = new AtomicInteger(1);

    public Account createAccount(double balance, String accountType, int accountNumber) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");

        }

        //int accountNumber = generateUniqueId();
        //need if statement below
        //if account number exists in the account repo than throw an runtime exception error ("Account number already exists")

        User user = new User();
        Account account = new Account();
        account.setAccountNumber(generateUniqueId());
        account.setAccountType(accountType);
        account.setBalance(balance);

        account.setUser(user); //GIVING ERROR FORCING NEW USER HERE
        return accountRepository.save(account);

    }

    private int generateUniqueId(){
        return nextId.getAndIncrement();
    }


//    private int generateAccountNumber() { //generate account number
//
//        //String uuid = UUID.randomUUID().toString();
//        //String accountNumber = uuid.replaceAll("[^0-9]", " ");
//        //Random random = new Random();
//
//
//
//        while (!accountRepository.existsByAccountNumber(accountNumber)){
//            //accountNumber = random.nextInt(999999999)
//
//        }
//        return accountNumber;
//        if(accountRepository.existsByAccountNumber((accountNumber))){
//
//        }
//    }

    //method to generate a unique account number? Make sure number generated in account number is unique.

//    private int generateUniqueAccountNumber(){
//
//    }


}


