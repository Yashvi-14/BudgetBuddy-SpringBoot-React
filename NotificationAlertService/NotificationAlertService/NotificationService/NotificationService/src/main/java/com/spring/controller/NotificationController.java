package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.service.NotificationService;

@RestController
//@CrossOrigin(origins = "http://localhost:3000") 
public class NotificationController {
	
	@Autowired
	NotificationService notificationService;
	
	@GetMapping("/checkLimit/{user_id}")
	public String checkLimit(@PathVariable("user_id") int user_id) {
		return notificationService.checkLimit(user_id);
	}
	
	@GetMapping("/checkDailyTransactions/{user_id}")
	public String checkDailyTransactions(@PathVariable("user_id") int user_id) {
		return notificationService.checkDailyTransactions(user_id);
	}
	
}
