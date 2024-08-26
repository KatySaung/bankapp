package com.example.final_project.dto;

public class CreateAccountRequestDTO {
    private Integer number;
    private Integer routNum;
    private String accountName;
    private Double openingBalance;
    private Integer[] transactions;
    private Double balance;
    private Integer customerId;

    public CreateAccountRequestDTO(int i, int i1, String accountName, Double openingBalance, Integer[] integers, Double openingBalance1, Integer customerId) {
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(Double openingBalance) {
        this.openingBalance = openingBalance;
    }

    public Integer getRoutNum() {
        return routNum;
    }

    public void setRoutNum(Integer routNum) {
        this.routNum = routNum;
    }

    public Integer[] getTransactions() {
        return transactions;
    }

    public void setTransactions(Integer[] transactions) {
        this.transactions = transactions;
    }
}
