package com.example.final_project.service;

import com.example.final_project.entities.Account;
import com.example.final_project.entities.User;
import com.example.final_project.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(double balance, String accountType, int accountNumber){
        if (balance < 0){
            throw new illegalArgumentException("Balance cannot be negative");

        }

        int accountNumber = generateAccountNumber();
        //need if statement below
        //if account number exists in the account repo than throw an runtime exception error ("Account number already exists")

        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setAccountType(accountType);
        account.setBalance(balance);

        //account.setUser(user); GIVING ERROR FORCING NEW USER HERE
        return accountRepository.save(account);

        //generate account number
        private int generateAccountNumber(){
//            int accountNumber;
            do {
                accountNumber = generateAccountNumber();
            } while (accountRepository.existsByAccountNumber(accountNumber)){
                return accountNumber;
            }
        }

        //method to generate a unique account number? Make sure number generated in account number is unique.
        /*
        private int generateUniqueAccountNumber(){

        }
        */


    }

}
