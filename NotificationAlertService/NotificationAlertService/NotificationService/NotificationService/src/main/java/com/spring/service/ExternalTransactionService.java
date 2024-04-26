package com.spring.service;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.spring.entity.Transaction;

@FeignClient(name = "BudgetbuddyTranactions", url = "http://localhost:6500")
public interface ExternalTransactionService {
	
	@GetMapping("/viewTransaction/{user_id}")
	public List<Transaction> viewTransaction(@PathVariable int user_id);

}
