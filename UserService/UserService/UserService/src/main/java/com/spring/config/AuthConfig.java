package com.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AuthConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    
        return http.csrf(csrf -> csrf.disable())
        		.cors(cors -> cors.disable())
        		.authorizeHttpRequests(auth -> auth
        				.requestMatchers("/**").permitAll()
//        	            .anyRequest().authenticated()
        	     )
        		.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//        		.cors(cors->cors.configurationSource(corsConfigurationSource()))
        		.headers(header -> header.disable())
                .build();
    }
	
//	private CorsConfigurationSource corsConfigurationSource() {
//		return new CorsConfigurationSource() { 
//			@Override
//			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//				CorsConfiguration cfg = new CorsConfiguration();
//				
//				cfg.setAllowedOrigins(Arrays.asList(
//					"http://localhost:3000/",
//					"http://localhost:8777/user-service/",
//					"http://localhost:9898/"
//				));
//		        cfg.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
//		        cfg.setAllowCredentials(true);
//		        cfg.setAllowedOriginPatterns(Arrays.asList(
//		        		"http://localhost:3000/**", 
//		        		"http://localhost:8777/user-service/**", "http://localhost:9898/**"));
//		        cfg.setAllowedHeaders(Arrays.asList("Content-Type", "X-Requested-With", "accept", "Origin", 
//		        		"Access-Control-Request-Method", "Access-Control-Request-Headers"));
//		        cfg.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin", 
//		        		"Access-Control-Allow-Credentials"));
//		        cfg.setMaxAge(3600L);
//				        
//				return cfg;
//			
//			}
//			
//		};
//	}

	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
	
	@Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }
	
	@Bean
    public AuthenticationProvider authenticationProvider(){
		
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        
        authenticationProvider.setUserDetailsService(userDetailsService());
        
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        
        return authenticationProvider;
        
    }

}
