package com.Budgetbuddytranactions.transactiondao;


import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.Budgetbuddytranactions.model.Transaction;



public interface TransactionDao {


	public Transaction addTransaction (Transaction transaction);
	public void deleteTransaction(int transaction_Id);
	public Transaction updateTransaction (int transaction_Id,Transaction transaction);
	public List<Transaction> viewTransaction(@PathVariable int user_id);
}

