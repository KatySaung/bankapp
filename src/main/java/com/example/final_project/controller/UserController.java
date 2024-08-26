package com.example.final_project.controller;

import com.example.final_project.dto.RegisterUserDto;
import com.example.final_project.dto.UserDTO;
import com.example.final_project.entities.Account;
import com.example.final_project.entities.User;
import com.example.final_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Retrieve all users
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    // Retrieve a user by user ID
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long userId) {
        UserDTO user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> register(@RequestBody RegisterUserDto user) {
        User newUser = userService.registerDTO(user.getName());
        return ResponseEntity.status(201).body(newUser);
    }

    // Delete a user by user ID
    @DeleteMapping("/{userId}")
    public ResponseEntity<Double> deleteUser(@PathVariable Long userId) {
        double totalFunds = userService.deleteUser(userId);
        return ResponseEntity.ok(totalFunds);
    }

    //display userinfo
    @GetMapping("/{accounts}")
    public Map<String, Object> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // Access user details
            String username = authentication.getName();
            ;
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("username", username);
            userinfo.put("accounts" , a );
            return userInfo;
        } else {
            // Handle case where user is not authenticated
            return new HashMap<>();
        }
    }
}


