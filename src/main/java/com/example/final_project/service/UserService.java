package com.example.final_project.service;

import com.example.final_project.dto.AccountDTO;
import com.example.final_project.dto.UserDTO;
import com.example.final_project.entities.Account;
import com.example.final_project.entities.User;
import com.example.final_project.repository.UserRepository;
import com.example.final_project.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User registerDTO(String name, String password) {
        if (userRepository.findByUsername(name) != null) {
            throw new IllegalArgumentException("Username already exists.");
        }

        User user = new User(name, password);
        validateUser(user);

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToUserDTO(user);
    }

    private UserDTO convertToUserDTO(User user) {
        List<AccountDTO> accountDTOs = user.getAccounts().stream()
                .map(account -> new AccountDTO(
                        account.getBalance(),
                        account.getAccountType(),
                        account.getAccountNumber(),
                        user.getUserId()
                ))
                .collect(Collectors.toList());

        return new UserDTO(user.getUserId(), user.getUsername(), accountDTOs);
    }

    public User login(String username, String password) {
        if (!ValidationUtils.isValidUsername(username)) {
            throw new IllegalArgumentException("Invalid username.");
        }
        if (!ValidationUtils.isValidPassword(password)) {
            throw new IllegalArgumentException("Invalid password.");
        }

        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        throw new IllegalArgumentException("Invalid username or password.");
    }

    @Transactional
    public double deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User ID not found."));

        double totalFunds = user.getAccounts().stream()
                .mapToDouble(Account::getBalance)
                .sum();

        userRepository.delete(user);
        return totalFunds;
    }

    private void validateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }
        if (!ValidationUtils.isValidUsername(user.getUsername())) {
            throw new IllegalArgumentException("Invalid username.");
        }
        if (!ValidationUtils.isValidPassword(user.getPassword())) {
            throw new IllegalArgumentException("Invalid password.");
        }
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> convertToUserDTO(user))
                .collect(Collectors.toList());
    }
}
