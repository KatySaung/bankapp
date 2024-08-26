package com.example.final_project.controller;

import com.example.final_project.dto.RegisterUserDto;
import com.example.final_project.dto.UserDTO;
import com.example.final_project.entities.User;
import com.example.final_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
