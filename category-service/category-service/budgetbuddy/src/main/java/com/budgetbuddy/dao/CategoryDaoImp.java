//package com.budgetbuddy.dao;
// 
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
// 
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
// 
//import com.budgetbuddy.category.Category;
//import com.budgetbuddy.categoryrepository.CategoryRepository;
// 
//@Component
//public class CategoryDaoImp implements CategoryDao {
// 
//	@Autowired
//	CategoryRepository repo;
//	List<Category> getAllCategory = new ArrayList<>();
// 
//	
//
// 
//	public Category addCategory(Category category,int user_id) {
//		  Category existingCategory = repo.findByNameIgnoreCase(category.getName());
//	    // If the category does not exist, save it to the database
//	    if (existingCategory == null) {
//	    	getAllCategory.add(category);
//	    	category.setUser_id(user_id);
//	        Category result = repo.save(category);
//	        return result;
//	    } else {
//	        // If the category already exists, return null or handle the situation as needed
//	        return null;
//	    }
//	}
//	public boolean deleteCategory(int cat_Id , int user_id) {
// 
//		List<Category> list = repo.findAll();
//		for(Category e:list) {
//			if(e.getCat_Id()== cat_Id && e.getUser_id() == user_id) {
//				repo.deleteById(cat_Id);
//				return true;
//			}
//		}
//		return false;
//	}
//
//	public Category updateCategory(int cat_Id, int budget_limit,int user_id) {
////		Optional<Category> op=repo.findById(cat_Id);
////		if(op.isPresent()) {
////			Category c=op.get();
////			c.setBudgetLimit(budget_limit);
////			repo.save(c);
////			return c;
////		}
////		return null;
//		List<Category> list = repo.findAll();
//		for(Category e:list) {
//			if(e.getCat_Id() == cat_Id && e.getUser_id()== user_id) {
//				e.setBudgetLimit(budget_limit);
//				repo.save(e);
//				return e;
//			}
//		}
//		return null;
//	}
//	public List<Category> getAllCategories(int user_id){
//		List<Category> listEmpty = new ArrayList<>();
//		List<Category> list1 = repo.findAll();
//		for(Category e:list1){
//			if(e.getUser_id()== user_id) {
//				listEmpty.add(e);
//			}
//		}
//  
//	if(listEmpty.size() == 0) {
//	
//		Category cat1 = new Category();
//		cat1.setName("Food");
//		cat1.setType("expenses");
//		cat1.setUser_id(user_id);
//		cat1.setBudgetLimit(1000);
//		
//		
//		Category cat2 = new Category();
//		cat2.setName("Travel");
//		cat2.setType("expenses");
//		cat2.setUser_id(user_id);
//		cat2.setBudgetLimit(1000);
//		
//		
//		Category cat3 = new Category();
//		cat3.setName("Rent");
//		cat3.setType("expenses");
//		cat3.setUser_id(user_id);
//		cat3.setBudgetLimit(1000);
//		
//		
//		Category cat4 = new Category();
//		cat4.setName("Groceries");
//		cat4.setType("expenses");
//		cat4.setUser_id(user_id);
//		cat4.setBudgetLimit(1000);
//		repo.save(cat1);
//		repo.save(cat2);
//		repo.save(cat3);
//		repo.save(cat4);
//		listEmpty.add(cat1);
//		listEmpty.add(cat2);
//		listEmpty.add(cat3);
//		listEmpty.add(cat4);
//		
//		return listEmpty;
//	}else {
//		
//		
//		
//		return listEmpty;
//	}
//	}
//
//	public Category getById(int cat_Id) {
//		Optional<Category>o= repo.findById(cat_Id);
//		Category c=o.get();
//		 if(o.isPresent()) {
//			 return c;
//		 }
//		 return null;
//	}
// 
//	
// 
//	
//}
package com.budgetbuddy.dao;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
 
import com.budgetbuddy.exception.NotAddedException;
import com.budgetbuddy.category.Category;
import com.budgetbuddy.categoryrepository.CategoryRepository;
 
@Component
public class CategoryDaoImp implements CategoryDao {
 
	@Autowired
	CategoryRepository repo;
	
	List<Category> getAllCategory = new ArrayList<>();
	
 
	public Category addCategory(Category category, int user_id) throws NotAddedException {
//	    List<Category> userCategories = repo.findByUserId(user_id);
		
		
		
		
		
		
	    List<Category> userCategories = new ArrayList<>();
		List<Category> list1 = repo.findAll();
		for(Category e:list1){
			if(e.getUser_id()== user_id) {
				
				userCategories.add(e);
			}
		}
	    // Check if the category already exists for the user
	    boolean categoryExists = userCategories.stream()
	                                           .anyMatch(cat -> cat.getName().equalsIgnoreCase(category.getName()));
 
	    if (!categoryExists) {
	        category.setUser_id(user_id);
	        Category result = repo.save(category);
	        return result;
	    } else {
	        throw new NotAddedException("Category already exists for the user.");
	    }
	}
 
	
 
//	public Category addCategory(Category category,int user_id) throws NotAddedException {
//		
//		
//		
//		List<Category> listEmpty = new ArrayList<>();
//		List<Category> list1 = repo.findAll();
//		for(Category e:list1){
//			if(e.getUser_id()== user_id) {
//				
//				listEmpty.add(e);
//			}
//		}
//		boolean CategoryExist=true;
//		for(Category e:listEmpty) {
//			if(e.getName()==category.getName()) {
//				 CategoryExist=false;
//			}
//		}
////		Category existingCategory = repo.findByNameIgnoreCase(category.getName());
//	    // If the category does not exist, save it to the database
//		  
//		
//	    if (CategoryExist == true) {
//	    	getAllCategory.add(category);
//	    	category.setUser_id(user_id);
//	        Category result = repo.save(category);
//	        return result;
//	    }else {
//		 throw new NotAddedException("not found");
//	}}
	
	public boolean deleteCategory(int cat_Id , int user_id)throws NotAddedException {
 
		List<Category> list = repo.findAll();
		for(Category e:list) {
			if(e.getCat_Id()== cat_Id && e.getUser_id() == user_id) {
				repo.deleteById(cat_Id);
				return true;
			}
		}
		throw new NotAddedException("not deleted");
		
	}
	
	
	public Category updateCategory(int cat_Id, int budget_limit,int user_id) throws NotAddedException{
//		Optional<Category> op=repo.findById(cat_Id);
//		if(op.isPresent()) {
//			Category c=op.get();
//			c.setBudgetLimit(budget_limit);
//			repo.save(c);
//			return c;
//		}
//		return null;
		
		List<Category> list = repo.findAll();
		for(Category e:list) {
			if(e.getCat_Id() == cat_Id && e.getUser_id()== user_id) {
				e.setBudgetLimit(budget_limit);
				repo.save(e);
				return e;
			}
		}
		throw new NotAddedException("not updated");
	}
	
	public List<Category> getAllCategories(int user_id){
		
		
 
			 List<Category> listEmpty = new ArrayList<>();
				List<Category> list1 = repo.findAll();
				for(Category e:list1){
					if(e.getUser_id()== user_id) {
						listEmpty.add(e);
					}
				}
		  
			if(listEmpty.size() == 0) {
			
				Category cat1 = new Category();
				cat1.setName("Food");
				cat1.setType("expenses");
				cat1.setUser_id(user_id);
				cat1.setBudgetLimit(1000);
				
				
				Category cat2 = new Category();
				cat2.setName("Travel");
				cat2.setType("expenses");
				cat2.setUser_id(user_id);
				cat2.setBudgetLimit(1000);
				
				
				Category cat3 = new Category();
				cat3.setName("Rent");
				cat3.setType("expenses");
				cat3.setUser_id(user_id);
				cat3.setBudgetLimit(1000);
				
				
				Category cat4 = new Category();
				cat4.setName("Groceries");
				cat4.setType("expenses");
				cat4.setUser_id(user_id);
				cat4.setBudgetLimit(1000);
				repo.save(cat1);
				repo.save(cat2);
				repo.save(cat3);
				repo.save(cat4);
				listEmpty.add(cat1);
				listEmpty.add(cat2);
				listEmpty.add(cat3);
				listEmpty.add(cat4);
				
				return listEmpty;
			}else {
				
				
				
				return listEmpty;
			}
		
		
	}
	
	
	public Category getById(int cat_Id) throws NotAddedException {
		Optional<Category>o= repo.findById(cat_Id);
		Category c=o.get();
		 if(o.isPresent()) {
			 return c;
		 }
		 throw new NotAddedException("not found");
	}
 
 
    // Add default categories for a new user or existing user upon login
//    public List<Category> addDefaultCategoriesForUser(int userId) {
//        // Check if default categories exist for the user
//    	List<Category> list = repo.findAll();
//    	List<Category>defaultCategory=new ArrayList<>();
//    	
//    	for(Category c:list) {
//        if (c.getUser_id()!=userId) {
//            Category foodCategory = new Category();
//            foodCategory.setCat_Id(1);
//            foodCategory.setName("Food");
//            foodCategory.setBudgetLimit(0); // Set default budget limit as needed
//            foodCategory.setType("Expense");
//            foodCategory.setUser_id(userId);
//            defaultCategory.add(foodCategory);
//            repo.save(foodCategory);
//
//            Category clothingCategory = new Category();
//            clothingCategory.setCat_Id(2);
//            clothingCategory.setName("Clothing");
//            clothingCategory.setBudgetLimit(0); // Set default budget limit as needed
//            clothingCategory.setType("Expense");
//            clothingCategory.setUser_id(userId);
//            defaultCategory.add(clothingCategory);
//            repo.save(clothingCategory);
//
//            // Add more default categories similarly if needed
//         }
//    	}
//    	return defaultCategory;
//    }
//
//	
 
	
}
