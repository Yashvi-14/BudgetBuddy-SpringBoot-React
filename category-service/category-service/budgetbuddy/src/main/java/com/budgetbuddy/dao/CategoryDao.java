//package com.budgetbuddy.dao;
// 
//import java.util.List;
// 
//import com.budgetbuddy.category.Category;
// 
//public interface CategoryDao {
//	public Category addCategory(Category category,int user_id);
//	public boolean deleteCategory(int cat_Id,int user_id);
//	public Category updateCategory(int cat_Id,int budget_limit ,int user_id);
//	public List<Category> getAllCategories(int user_id);
//	public Category getById(int cat_Id);
//}
 package com.budgetbuddy.dao;
 
import java.util.List;
 
import com.budgetbuddy.exception.NotAddedException;
import com.budgetbuddy.category.Category;
 
public interface CategoryDao {
	
	public Category addCategory(Category category,int user_id) throws NotAddedException;
	public boolean deleteCategory(int cat_Id,int user_id) throws NotAddedException;
	public Category updateCategory(int cat_Id,int budget_limit ,int user_id) throws NotAddedException;
	public List<Category> getAllCategories(int user_id);
	public Category getById(int cat_Id) throws NotAddedException;
//	public List<Category> addDefaultCategoriesForUser(int userId);
	
}
 