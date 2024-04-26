package com.spring;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.spring.entity.UserCredential;
import com.spring.exception.InvalidCredential;
import com.spring.repository.UserCredentialRepository;
import com.spring.service.AuthService;
import com.spring.service.JwtService;
 
@SpringBootTest
class UserServiceApplicationTests {
	@Autowired
	private UserCredentialRepository repository;
	@Autowired
	private AuthService service;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtService jwtService;
 
	@Before
    public void setUp() {
		service = new AuthService();
    }
	@Test
    public void testSaveNewUser() throws InvalidCredential {
        UserCredential userCredential = createUserCredential();
        String result = service.saveUser(userCredential);
        assertEquals("User Added Successfully", result);
    }
    @Test
    public void testSaveExistingUser() {
        UserCredential userCredential = createUserCredential();
        repository.save(userCredential);
        InvalidCredential exception = assertThrows(InvalidCredential.class, () -> {
            service.saveUser(userCredential);
        });
        assertEquals("User Already Present", exception.getMessage());
    }
    @Test
    public void testGenerateToken() {
        UserCredential userCredential = createUserCredential();
        Map<String, UserCredential> claim = new HashMap<>();
        claim.put("user", userCredential);
        String token = jwtService.generateToken(userCredential.getEmail(), claim);
        assertNotNull(token);
        assertTrue(token.length() > 0);
    }
    @Test
    public void testValidateToken() {
        UserCredential userCredential = createUserCredential();
        Map<String, UserCredential> claim = new HashMap<>();
        claim.put("user", userCredential);
        String token = jwtService.generateToken(userCredential.getEmail(), claim);
        assertDoesNotThrow(() -> jwtService.validateToken(token));
    }
    private UserCredential createUserCredential() {
        UserCredential userCredential = new UserCredential();
        Random random = new Random();
        int randomNum = random.nextInt(1000);
        String email = "test" + randomNum + "@gmail.com";
        userCredential.setFirstName("Test");
        userCredential.setLastName("User");
        userCredential.setEmail(email);
        userCredential.setPassword(passwordEncoder.encode("1234"));
        return userCredential;
    }
}