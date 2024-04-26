package com.spring.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.spring.entity.UserCredential;
import com.spring.exception.InvalidCredential;
import com.spring.repository.UserCredentialRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {
	
	@Autowired
	private UserCredentialRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    private JwtService jwtService;
	
	public String saveUser(@RequestBody UserCredential userCredential) {
		
		Optional<UserCredential> isUserPresent = repository.findByEmail(userCredential.getEmail());
		
		if(isUserPresent.isPresent()) {
			throw new InvalidCredential("User Already Present");
		} else {
			
			userCredential.setPassword(passwordEncoder.encode(userCredential.getPassword()));
			
			UserCredential user = repository.save(userCredential);
			
			if (user == userCredential) {
			
				log.info("User Added Successfully");
				
				return "User Added Successfully";
				
			} else {
				
				log.error("Not Able to Add User");
				
				return "Not Able to Add User";
				
			}
			
		}
		
	}
	
	public String generateToken(String email) {
		
		Optional<UserCredential> user = repository.findByEmail(email);
		
		Map<String, UserCredential> claim = new HashMap<String, UserCredential>();
		
		if(user.isPresent()) {
			
			claim.put("user", user.get());
			
		}
		
		String token = jwtService.generateToken(email, claim);
		
		log.info("Token Generated Successfully");
		
		return token;
    }

    public void validateToken(String token) {
        
    	jwtService.validateToken(token);
        
        log.info("Token Validated Successfully");
        
    }

}
