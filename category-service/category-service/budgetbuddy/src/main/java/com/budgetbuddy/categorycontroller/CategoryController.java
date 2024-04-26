
//package com.budgetbuddy.categorycontroller;
// 
//import java.util.List;
// 
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.web.bind.annotation.CrossOrigin;
//
//import org.springframework.web.bind.annotation.DeleteMapping;
//
//import org.springframework.web.bind.annotation.GetMapping;
//
//import org.springframework.web.bind.annotation.PathVariable;
//
//import org.springframework.web.bind.annotation.PostMapping;
//
//import org.springframework.web.bind.annotation.PutMapping;
//
//import org.springframework.web.bind.annotation.RequestBody;
//
//import org.springframework.web.bind.annotation.RestController;
// 
//import com.budgetbuddy.category.Category;
//
//import com.budgetbuddy.categoryservice.CategoryServiceIn;
// 
//@RestController
//
////@CrossOrigin(origins = "http://localhost:3000")
//
//public class CategoryController {
// 
//	@Autowired
//
//	CategoryServiceIn cat;
//
//	@GetMapping("/getAll/{user_id}")
//
//	public List<Category> getAllCategories(@PathVariable int user_id)
//
//	{
//
//		return cat.getAllCategories(user_id);
//
//	}
//
//	@GetMapping("/getById/{cat_Id}")
//
//	public Category getById(@PathVariable int cat_Id) {
//
//		return cat.getById(cat_Id);
//
//	}
//
//	@PostMapping("/addCategory/{user_id}")
//
//	public Category addCategory( @RequestBody Category category ,@PathVariable int user_id)
//
//	{
//
//		return  cat.addCategory(category, user_id);	
//
//	}
//
//	@DeleteMapping("/deleteCategory/{cat_Id}/{user_id}")
//
//	public boolean deleteCategory(@PathVariable int cat_Id ,@PathVariable int user_id)
//
//	{
//
//		return cat.deleteCategory(cat_Id, user_id);
//
//	}
//
//	@PutMapping("/updateCategory/{cat_Id}/{budget_limit}/{user_id}")
//
//	public Category updateCategory(@PathVariable int cat_Id,@PathVariable int budget_limit ,@PathVariable int user_id)
//
//	{
//
//		return cat.updateCategory(cat_Id,budget_limit,user_id);
//
//	}
// 
//}




package com.budgetbuddy.categorycontroller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
 
import com.budgetbuddy.exception.NotAddedException;
import com.budgetbuddy.category.Category;
import com.budgetbuddy.categoryservice.CategoryServiceIn;
 
@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {
 
    @Autowired
    CategoryServiceIn cat;
 
    @GetMapping("/getAll/{user_id}")
    public ResponseEntity<List<Category>> getAllCategories(@PathVariable int user_id) {
        List<Category> categories = cat.getAllCategories(user_id);
        return ResponseEntity.ok(categories);
    }
 
    @GetMapping("/getById/{cat_Id}")
    public ResponseEntity<Category> getById(@PathVariable int cat_Id) {
        try {
            Category category = cat.getById(cat_Id);
            return ResponseEntity.ok(category);
        } catch (NotAddedException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
 
    @PostMapping("/addCategory/{user_id}")
    public ResponseEntity<Category> addCategory(@RequestBody Category category, @PathVariable int user_id) {
        try {
            Category addedCategory = cat.addCategory(category, user_id);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedCategory);
        } catch (NotAddedException ex) {
            return ResponseEntity.badRequest().build();
        }
    }
 
    @DeleteMapping("/deleteCategory/{cat_Id}/{user_id}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable int cat_Id, @PathVariable int user_id) {
        try {
            boolean deleted = cat.deleteCategory(cat_Id, user_id);
            if (deleted) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.ok(false);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
 
 
    @PutMapping("/updateCategory/{cat_Id}/{budget_limit}/{user_id}")
    public ResponseEntity<Category> updateCategory(@PathVariable int cat_Id, @PathVariable int budget_limit,
            @PathVariable int user_id) {
        try {
            Category updatedCategory = cat.updateCategory(cat_Id, budget_limit, user_id);
            if (updatedCategory != null) {
                return ResponseEntity.ok(updatedCategory);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NotAddedException ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
 