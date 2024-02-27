package com.tatva.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tatva.entities.Users;
import com.tatva.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
//    @Autowired
//   private Users users;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Users users) {
    	Users savedUser= null;
        ResponseEntity response = null;
        try {
            String hashPwd = passwordEncoder.encode(users.getPwd());
            users.setPwd(hashPwd);
            users.setCreateDt(String.valueOf(new Date(System.currentTimeMillis())));
            savedUser = userRepository.save(users);
            if (savedUser.getId() > 0) {
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Given user details are successfully registered");
            }
        } catch (Exception ex) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + ex.getMessage());
        }
        return response;
    }

    @RequestMapping("/user")
    public Users getUserDetailsAfterLogin(Authentication authentication) {
        List<Users> users = userRepository.findByEmail(authentication.getName());
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }
    
    
    @GetMapping("/comein")
    public ResponseEntity login(@RequestHeader("username") String username,@RequestHeader("password")  String password) {
 
    	List<Users> users = userRepository.findByEmail(username);
    	
        if (users.size() <1) 
        	//new UsernameNotFoundException("User not exists by Username");}
        	return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("user not found");
        if(passwordEncoder.matches(password, users.get(0).getPwd()))
        {
        	return ResponseEntity.ok("Invalid password");
        }
        else {
        	return ResponseEntity
        			.status(HttpStatus.BAD_REQUEST)
                    .body("invalid password");
        }
    	
    }
    
}

