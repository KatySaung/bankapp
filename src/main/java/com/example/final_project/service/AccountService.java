package com.example.final_project.service;

import com.example.final_project.dto.AccountDTO;
import com.example.final_project.dto.CreateAccountRequestDTO;
import com.example.final_project.dto.CreateAccountResponseDTO;

import java.util.List;

public interface AccountService {
    public AccountDTO getAccountByNumber(int accountNumber);
    public CreateAccountResponseDTO createAccount(CreateAccountRequestDTO requestDto);
    public double deleteAccount(Integer accountNumber);
    public int generateUniqueAccountNumber();
    public List<AccountDTO> getAllAccounts();
}
