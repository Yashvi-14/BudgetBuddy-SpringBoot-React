package com.spring.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring.entity.UserCredential;

public class CustomUserDetails implements UserDetails {

	private int id;
	private String firstName;
	private String lastName;
	private String email;
    private String password;

    public CustomUserDetails(UserCredential userCredential) {
    	this.id = userCredential.getId();
    	this.firstName = userCredential.getFirstName();
    	this.lastName = userCredential.getLastName();
        this.email = userCredential.getEmail();
        this.password = userCredential.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() { // See the declaration of UserDetail (getUsername is used)
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}