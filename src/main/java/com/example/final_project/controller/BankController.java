package com.example.final_project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
@CrossOrigin
public class BankController {
    @GetMapping("/sortCode")
    public ResponseEntity<String> getSortCode() {
        return ResponseEntity.ok("1234");
    }
}
