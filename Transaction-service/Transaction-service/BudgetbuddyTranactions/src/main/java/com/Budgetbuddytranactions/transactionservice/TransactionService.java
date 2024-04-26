package com.Budgetbuddytranactions.transactionservice;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.Budgetbuddytranactions.model.Transaction;
import com.Budgetbuddytranactions.transactiondao.TransactionDao;


@Service
public class TransactionService implements TransactionServiceIn{

	@Autowired
	TransactionDao tranDao;
	
	public Transaction addTransaction(Transaction transaction) {
		return tranDao.addTransaction(transaction);
	}
	
	public void deleteTransaction(int transaction_id) {
		tranDao.deleteTransaction(transaction_id);
	}
	public Transaction updateTransaction(int transaction_id,Transaction transaction) {
		return tranDao.updateTransaction(transaction_id, transaction);
	}
	public List<Transaction> viewTransaction(@PathVariable int user_id){
		return tranDao.viewTransaction(user_id);
	}
}

