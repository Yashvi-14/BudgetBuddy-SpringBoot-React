package com.Budgetbuddytranactions.transactioncontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Budgetbuddytranactions.model.Category;
import com.Budgetbuddytranactions.model.Transaction;
import com.Budgetbuddytranactions.transactionservice.ExternalService;
import com.Budgetbuddytranactions.transactionservice.TransactionServiceIn;





@RestController
//@CrossOrigin
public class TransactionController {

	
	
	
	@Autowired
	TransactionServiceIn trans;
	
	@Autowired
	private ExternalService extra;
	
	@PostMapping("/addTransaction")
	public Transaction addTransaction(@RequestBody Transaction transaction)
	{
	  return  trans.addTransaction(transaction);	
	}
	
//	@PostMapping("/addTransaction/{user_id}")
//	public Transaction addTransaction(@PathVariable int user_id, @RequestBody Transaction transaction)
//	{
//	  return  trans.addTransaction(transaction);	
//	}
	@DeleteMapping("/deleteTransaction/{transaction_id}")
	public void deleteTransaction(@PathVariable int transaction_id)
	{
		trans.deleteTransaction(transaction_id);
	}
	@PutMapping("/updateTransaction/{transaction_Id}")
	public Transaction updateTransaction(@PathVariable int transaction_Id,@RequestBody Transaction transaction)
	{
		return trans.updateTransaction(transaction_Id,transaction);
	}
	
	@GetMapping("/viewTransaction/{user_id}")
	public List<Transaction> viewTransaction(@PathVariable int user_id){
	
//		return trans.addTransaction(user_id);
		return trans.viewTransaction(user_id);
		
	}
	
//	@GetMapping("/getById/{cat_Id}/{user_Id}")
//	public Category getById(@PathVariable int cat_Id,@PathVariable int user_Id) {
//		return extra.getById(cat_Id,user_Id);
//	}
	
	
	
	
}
