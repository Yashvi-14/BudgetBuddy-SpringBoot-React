package com.Budgetbuddytranactions.transactiondao;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.Budgetbuddytranactions.model.Category;
import com.Budgetbuddytranactions.model.Transaction;
import com.Budgetbuddytranactions.repository.TransactionRepository;
import com.Budgetbuddytranactions.transactionservice.ExternalService;




@Component
public class TransactionDaoImp implements TransactionDao {
	
	@Autowired
	TransactionRepository repo;
	
	@Autowired
	ExternalService extra;
	
	List<Transaction>list=new ArrayList<>();
	
	public Transaction addTransaction(Transaction transaction) {
		list.add(transaction);
		List<Category> list = extra.getAllCategories(transaction.getUserId());
		String cateName = transaction.getCategoryName();
		for(Category e: list) {
			if(e.getName().equals(cateName)) {
				transaction.setCat_Id(e.getCat_Id());
				transaction.setType(e.getType());
			}
		}
		Transaction result = repo.save(transaction);	
		return result;
	}
	
	public void deleteTransaction(int transaction_Id) {
		repo.deleteById(transaction_Id);
	}
	
	public List<Transaction> viewTransaction(int user_id){
	
 	 	List<Transaction> list=repo.findByUserId(user_id);
 	 	if(list.isEmpty()) {
 	 		return null;
 	 	}else
 	 	{
 	 		return list;
 	 	}
 
	}
	
//	public Transaction updateTransaction(int transaction_Id, Transaction transaction) {
//		Optional<Transaction> op=repo.findById(transaction_Id);
//		if(op.isPresent()) {
//			Transaction t =op.get();
//			List<Category> list = extra.getAllCategories(t.getUserId());
//			int cat_id = 0 ;
//			for(Category e:list) {
//				if(e.getName().equals(transaction.getCategoryName())){
//					cat_id = e.getCat_Id();
//				}
//			}
//			if(cat_id != 0) {
//				t.setCategoryName(transaction.getCategoryName());
//				t.setCat_Id(cat_id);
//				t.setType(transaction.getType());
//				t.setNotes(transaction.getNotes());
//				t.setAmount(transaction.getAmount());
//				t.setTran_Date(transaction.getTran_Date());
//				repo.save(t);
//				System.out.println("hdidjf");
//				return t;
//			}
//		
//		}
//		return null;
//	}
	public Transaction updateTransaction(int transaction_Id, Transaction transaction) {
		Optional<Transaction> op=repo.findById(transaction_Id);
		if(op.isPresent()) {
			Transaction t =op.get();
			List<Category> list = extra.getAllCategories(t.getUserId());
			int cat_id = 0 ;
			for(Category e:list) {
				if(e.getName().equalsIgnoreCase(transaction.getCategoryName())){
					cat_id = e.getCat_Id();
				}
			}
			t.setAmount(transaction.getAmount());
			t.setCat_Id(cat_id);
			t.setNotes(transaction.getNotes());
			t.setTran_Date(transaction.getTran_Date());
			t.setCategoryName(transaction.getCategoryName());
//			t.setType(transaction.getType());
			repo.save(t);
				return t;
			}
		
		return null;
	}
	
	
	

}

