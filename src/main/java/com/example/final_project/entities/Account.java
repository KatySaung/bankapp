package com.example.final_project.entities;

public class Account {
    private double balance;
    private String accountType;
    private int accountNumber;

    //constructor

    public Account(double balance, String accountType, int accountNumber) {
        this.balance = balance;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
    }
    public Account(){}

    //setter/ getter

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
}
