package com.tatva.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AccountController {


    @GetMapping("/myAccount")
    public ResponseEntity<String> getAccountDetails() {
    	return ResponseEntity.ok("Hello mohit");
    }
    
//    @GetMapping("/l")
//    public ResponseEntity<String> getAccountDetails() {
//    	return ResponseEntity.ok("Hello mohit");
//    }

}
