package microservice3.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import microservice3.entity.Transaction;
import microservice3.repository.repository;

@Service
public class ReportService {
	
	
	@Autowired
	repository repo;
	
	@Autowired
	RandomService r;
	
	public List<Transaction> viewTran(int user_id){
		return r.viewTransaction(user_id);
	}
	
	
	
	public double getExpenseByType(int user_id) {
		List<Transaction> list = r.viewTransaction(user_id);
		double expense = 0;
		for(Transaction e:list) {
			if(e.getType().equalsIgnoreCase("expense")) {
				expense += e.getAmount();
				
			}
		}
		return expense;
	}
	
	public double getIncomeByType(int user_id) {
		List<Transaction> list = r.viewTransaction(user_id);
		double income = 0;
		for(Transaction e:list) {
			if(e.getType() != null && e.getType().equalsIgnoreCase("income") ) {
				income += e.getAmount();
				
			}
		}
		return income;
	}
	
	
	public HashMap<String,Double> getAmountByCategories(int user_id){
		List<Transaction> list = r.viewTransaction(user_id);
		HashMap<String,Double> result = new HashMap<>();
		for(Transaction e:list) {
			if(result.containsKey(e.getCategoryName()) == false){
				result.put(e.getCategoryName(), e.getAmount());
			}else {
				result.put(e.getCategoryName(), result.get(e.getCategoryName())+e.getAmount());
			}
		}
		
		return result;
	}
	
	
	

}
