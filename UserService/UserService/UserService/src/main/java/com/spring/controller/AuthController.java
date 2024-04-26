package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.spring.entity.LoginCredential;
import com.spring.entity.UserCredential;
import com.spring.exception.InvalidCredential;
import com.spring.service.AuthService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = "*")
public class AuthController {
    
	@Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signUp")
    public ResponseEntity<String> addNewUser(@RequestBody @Valid UserCredential user) {
    	
    	String message = service.saveUser(user);
    	
        return new ResponseEntity<String>(message, null, HttpStatus.OK); // we don't want to add header
        
    }

    @PostMapping("/login")
    public ResponseEntity<String> getToken(@RequestBody LoginCredential loginCredential) {
       
    	Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginCredential.getEmail(), loginCredential.getPassword()));

        if (authenticate.isAuthenticated()) {
        	String token = service.generateToken(loginCredential.getEmail());
            return new ResponseEntity<String>(token, null, HttpStatus.OK);
        } else {
            throw new InvalidCredential("Invalid access");
        }
        
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestParam("token") String token) {
        
    	service.validateToken(token);
        
        return new ResponseEntity<String>("Token is valid", null, HttpStatus.OK);
    }

}
