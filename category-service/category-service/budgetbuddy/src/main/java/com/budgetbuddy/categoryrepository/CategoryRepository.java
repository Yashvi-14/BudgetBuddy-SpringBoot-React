
//package com.budgetbuddy.categoryrepository;
// 
//import java.util.List;
// 
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import org.springframework.stereotype.Repository;
// 
//import com.budgetbuddy.category.Category;
// 
//@Repository
//
//public interface CategoryRepository extends JpaRepository<Category,Integer>
//
//{
// 
//	 public Category findByNameIgnoreCase(String name);
//
////	 public List<Category> findByUserId(int user_id);
// 
//}
package com.budgetbuddy.categoryrepository;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.budgetbuddy.category.Category;
 
@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer>
{
 
	 public Category findByNameIgnoreCase(String name);
//	  public List<Category> findByUserId(int userId);
//	 public List<Category> findByUserId(int user_id);
 
}