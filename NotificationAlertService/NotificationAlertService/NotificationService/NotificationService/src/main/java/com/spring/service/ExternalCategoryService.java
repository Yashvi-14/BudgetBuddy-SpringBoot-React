package com.spring.service;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.entity.Category;

@FeignClient(name = "budgetbuddy", url = "http://localhost:7001")
public interface ExternalCategoryService {
	
	@GetMapping("/getAll/{user_id}")
	public List<Category> getAllCategories(@PathVariable int user_id);

}
