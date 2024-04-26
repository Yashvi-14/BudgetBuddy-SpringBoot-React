package com.Budgetbuddytranactions.transactionservice;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;

import com.Budgetbuddytranactions.model.Category;



@FeignClient(name = "budgetbuddy", url = "http://localhost:7001")
public interface ExternalService {

	
	@GetMapping("/getById/{cat_Id}/{user_Id}")
	public Category getById(@PathVariable int cat_Id ,@PathVariable int user_Id);
	
	
	@GetMapping("/getAll/{user_Id}")
	public List<Category> getAllCategories(@PathVariable int user_Id);
	
	@PostMapping("/addCategory/{user_Id}")
	public Category addCategory( @RequestBody Category category , @PathVariable int user_Id);
	
	
	
	
}
