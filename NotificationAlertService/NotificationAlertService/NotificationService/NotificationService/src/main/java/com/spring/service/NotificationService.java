package com.spring.service;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.entity.Category;
import com.spring.entity.Transaction;
@Service
 
public class NotificationService {
 
	@Autowired
	public ExternalCategoryService getCategory;
 
	@Autowired
	public ExternalTransactionService getTransaction;
 
 
	public String checkLimit(int user_id) {
 
		List<Transaction> transactions = getTransaction.viewTransaction(user_id);
		List<Category> categories = getCategory.getAllCategories(user_id);
 
		if(transactions != null)
		{
			int length = transactions.size();
			String categoryName = transactions.get(length - 1).getCategoryName();
			int categoryLimit = 0;
			double totalExpense = 0;
			for(Category category : categories) 
			{
				if(category.getName().equals(categoryName))
				{
					categoryLimit = category.getBudgetLimit();
				}
			}
 
			if(categoryLimit != 0)
				{
				for(Transaction transaction : transactions)
				{
					if(transaction.getCategoryName().equals(categoryName))
					{
						totalExpense += transaction.getAmount();
					}
         		}
 
				if(totalExpense > categoryLimit) {
					double difference = totalExpense - categoryLimit;
					return "For " + categoryName + " limit exceeded by " + difference 
							+ ". Your actual limit is " + categoryLimit 
							+ ". Your total expense in " + categoryName + " is " + totalExpense;
 
				}
				return "No Limit exceeded";
			} 
		}
		return "No Limit exceeded";
	}
 
	public String checkDailyTransactions(int user_id) {
 
		List<Transaction> transactions = getTransaction.viewTransaction(user_id);
		LocalDate today = LocalDate.now();
		String yesterday = today.minusDays(1).toString();
		if(transactions != null) 
		{
			for(Transaction transaction : transactions)
			{
				if(transaction.getTran_Date().substring(0, 10).equals(yesterday)) 
				{
					return "Expense submitted for today.";
 
				} 
			}
		}
 
		return "You haven't submitted your expenses for today.";
	}
}
