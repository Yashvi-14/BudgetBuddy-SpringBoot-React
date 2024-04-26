package com.Budgetbuddytranactions.transactionservice;



import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.Budgetbuddytranactions.model.Transaction;



public interface TransactionServiceIn {
	

	public Transaction addTransaction(Transaction transaction);
	public void deleteTransaction(int transaction_id);
	public Transaction updateTransaction(int transaction_id,Transaction transaction);
	public List<Transaction> viewTransaction(@PathVariable int user_id);

}
