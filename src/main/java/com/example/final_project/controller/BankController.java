package com.example.final_project.controller;//package com.example.final_project.controller;
//
//import com.example.final_project.service.BankService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api")
//public class BankController {
//
//    @Autowired
//    private BankService bankService;
//
//    @GetMapping("/sortCode")
//    public ResponseEntity<String> getSortCode() {
//        try {
//            String sortCode = bankService.getSortCode();
//            return ResponseEntity.ok(sortCode);
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
//        }
//    }
//}

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {

    @GetMapping("/sortCode")
    public ResponseEntity<String> getSortCode() {
        return ResponseEntity.ok("1234"); // or fetch from a service
    }
}

