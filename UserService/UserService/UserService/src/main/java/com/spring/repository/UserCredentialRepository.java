package com.spring.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.entity.UserCredential;

public interface UserCredentialRepository  extends JpaRepository<UserCredential,Integer> {
	
    Optional<UserCredential> findByEmail(String email);
    
}
