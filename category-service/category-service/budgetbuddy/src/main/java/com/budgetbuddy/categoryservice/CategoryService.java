//package com.budgetbuddy.categoryservice;
// 
//import java.util.List;
// 
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
// 
//import com.budgetbuddy.category.Category;
//import com.budgetbuddy.dao.CategoryDao;
// 
//@Service
//public class CategoryService implements CategoryServiceIn {
// 
//	
//	@Autowired
//	CategoryDao catDao;
//	public Category addCategory(Category category ,int user_id) {
//		return catDao.addCategory(category, user_id);
//	}
//	public boolean deleteCategory(int cat_Id,int user_id) {
//		return catDao.deleteCategory(cat_Id ,user_id);
//	}
//	public Category updateCategory(int cat_Id,int budget_limit,int user_id) {
//		return catDao.updateCategory(cat_Id, budget_limit,user_id);
//	}
//	public List<Category> getAllCategories(int user_id){
//		return catDao.getAllCategories( user_id);
//	}
//	public Category getById(int cat_Id) {
//		return catDao.getById(cat_Id);
//	}
// 
//	
//}
package com.budgetbuddy.categoryservice;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.budgetbuddy.exception.NotAddedException;
import com.budgetbuddy.category.Category;
import com.budgetbuddy.dao.CategoryDao;
 
@Service
public class CategoryService implements CategoryServiceIn {
 
	
	@Autowired
	CategoryDao catDao;
	public Category addCategory(Category category ,int user_id) throws NotAddedException {
		return catDao.addCategory(category, user_id);
	}
	public boolean deleteCategory(int cat_Id,int user_id) throws NotAddedException {
		return catDao.deleteCategory(cat_Id ,user_id);
	}
	public Category updateCategory(int cat_Id,int budget_limit,int user_id) throws NotAddedException {
		return catDao.updateCategory(cat_Id, budget_limit,user_id);
	}
	public List<Category> getAllCategories(int user_id){
		return catDao.getAllCategories( user_id);
	}
	public Category getById(int cat_Id) throws NotAddedException {
		return catDao.getById(cat_Id);
	}
 
	
}