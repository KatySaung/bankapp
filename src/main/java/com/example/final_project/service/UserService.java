//package com.example.final_project.service;
//
//import com.example.final_project.entities.User;
//import com.example.final_project.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public User registerUser(User user) {
//        return userRepository.save(user);
//    }
//
//    public User login(String username, String password) {
//        User user = userRepository.findByUsername(username);
//        if (user != null && user.getPassword().equals(password)) {
//            return user;
//        }
//        return null;
//    }
//
//    public void deleteUser(Long userId) {
//        userRepository.deleteById(userId);
//    }
//}


package com.example.final_project.service;

import com.example.final_project.entities.User;
import com.example.final_project.repository.UserRepository;
import com.example.final_project.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User registerUser(User user) {
        validateUser(user);

        // Ensure the username is unique
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists.");
        }

        // Save the new user
        return userRepository.save(user);
    }

    public User login(String username, String password) {
        // Validate input
        if (!ValidationUtils.isValidUsername(username)) {
            throw new IllegalArgumentException("Invalid username.");
        }
        if (!ValidationUtils.isValidPassword(password)) {
            throw new IllegalArgumentException("Invalid password.");
        }

        // Find the user by username
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        // If no match is found, throw an exception or return null
        throw new IllegalArgumentException("Invalid username or password.");
    }

    @Transactional
    public void deleteUser(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        } else {
            throw new IllegalArgumentException("User ID not found.");
        }
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
}
